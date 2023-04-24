package com.lelestacia.lelenime.core.data.repository

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.data.JikanErrorParserUtil
import com.lelestacia.lelenime.core.data.mapper.asCharacter
import com.lelestacia.lelenime.core.data.mapper.asCharacterWithVoiceActorEntities
import com.lelestacia.lelenime.core.data.mapper.asNewEntity
import com.lelestacia.lelenime.core.data.mapper.asNewVoiceActor
import com.lelestacia.lelenime.core.database.animeStuff.entity.anime.AnimeCharacterReferenceEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterVoiceActorCrossRefEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.voiceActor.VoiceActorEntity
import com.lelestacia.lelenime.core.database.animeStuff.service.ICharacterDatabaseService
import com.lelestacia.lelenime.core.model.character.Character
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse
import com.lelestacia.lelenime.core.network.source.IAnimeNetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val animeNetworkService: IAnimeNetworkService,
    private val characterDatabaseService: ICharacterDatabaseService,
    private val errorParser: JikanErrorParserUtil = JikanErrorParserUtil(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ICharacterRepository {

    /**
     * Retrieves a list of characters associated with the specified anime ID from the local database or the API.
     * If the data is available in the local database and is up-to-date, it will be returned immediately.
     * Otherwise, the function will fetch the data from the API and insert it into the local database before returning it.
     *
     * @param animeID The ID of the anime to retrieve characters for.
     * @return A flow that emits a [Resource] object with a list of [Character] objects on success, or an error message on failure.
     *         The flow will also emit a loading state before starting the retrieval process.
     */

    override fun getAnimeCharactersByAnimeID(animeID: Int): Flow<Resource<List<Character>>> =
        flow<Resource<List<Character>>> {
            val animeCharactersCrossRef: List<AnimeCharacterReferenceEntity> =
                characterDatabaseService.getCharactersByAnimeID(animeID = animeID)

            if (animeCharactersCrossRef.isEmpty()) {

                val apiResponse: List<CharacterResponse> =
                    animeNetworkService.getAnimeCharactersByAnimeID(animeID = animeID)

                if (apiResponse.isEmpty()) {

                    /**
                     * Returning here, because since the API doesn't return anything,
                     * that means the data on the server are also empty
                     */

                    emit(Resource.Success(data = emptyList()))
                    return@flow
                }

                val characterEntities: List<CharacterEntity> =
                    apiResponse.map(CharacterResponse::asNewEntity)

                val characterWithVoiceActors: List<Pair<Int, List<VoiceActorEntity>>> =
                    apiResponse.map(CharacterResponse::asCharacterWithVoiceActorEntities)

                characterDatabaseService.insertCharactersAndVoiceActors(
                    characters = characterEntities,
                    voiceActors = characterWithVoiceActors.map { pair ->
                        pair.second
                    }.flatten(),
                    animeCharactersCrossRef = apiResponse.map { networkCharacter ->
                        AnimeCharacterReferenceEntity(
                            animeID = animeID,
                            characterID = networkCharacter.characterData.malID
                        )
                    },
                    characterVoiceActorsCrossRef = characterWithVoiceActors.map { pair ->
                        pair.second.map { voiceActorEntity ->
                            CharacterVoiceActorCrossRefEntity(
                                characterID = pair.first,
                                voiceActorID = voiceActorEntity.voiceActorID
                            )
                        }
                    }.flatten()
                ).also {
                    emit(Resource.Success(data = characterEntities.map { it.asCharacter() }))
                    return@flow
                }

                /**
                 *  At this point, characters' data, voice actors data,
                 *  and its corresponding reference have been inserted into its own table to be queried in further usage.
                 *  Also, there's no need to continue the process since the data are coming straight from the API
                 *  and don't need further inspection
                 */
            }

            val charactersID: List<Int> = animeCharactersCrossRef.map { it.characterID }
            val localCharacters: List<CharacterEntity> =
                characterDatabaseService.getCharactersByCharacterID(characterID = charactersID)
            val localVoiceActors =
                characterDatabaseService.getVoiceActorsByCharacterID(characterID = charactersID)

            emit(Resource.Success(data = localCharacters.map(CharacterEntity::asCharacter)))

            val oldestUpdate: Long = localCharacters.minOf {
                (it.updatedAt ?: it.createdAt).time
            }
            val timeDifference: Long = Date().time - oldestUpdate
            val isDataOutDated = (TimeUnit.MILLISECONDS.toHours(timeDifference) % 24).toInt() > 24

            if (isDataOutDated) {
                val apiResponse: List<CharacterResponse> = animeNetworkService.getAnimeCharactersByAnimeID(animeID = animeID)

                val newCharacters: List<CharacterEntity> =
                    apiResponse.map(CharacterResponse::asNewEntity)
                val newVoiceActors: List<VoiceActorEntity> =
                    apiResponse.map(CharacterResponse::asNewVoiceActor).flatten()

                characterDatabaseService.updateCharactersAndVoiceActors(
                    characters = Pair(localCharacters, newCharacters),
                    voiceActors = Pair(localVoiceActors, newVoiceActors)
                )

                emit(Resource.Success(data = newCharacters.map { it.asCharacter() }))
            }
        }.onStart {
            emit(Resource.Loading)
        }.catch { t ->
            when (t) {
                is HttpException -> emit(Resource.Error(
                    data = null,
                    message = errorParser(t)
                ))
                else -> emit(Resource.Error(
                    data = null,
                    message = "Error: ${t.message}"
                ))
            }
        }.flowOn(ioDispatcher)
}