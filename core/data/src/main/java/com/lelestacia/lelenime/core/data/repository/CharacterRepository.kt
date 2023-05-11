package com.lelestacia.lelenime.core.data.repository

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.data.JikanErrorParserUtil
import com.lelestacia.lelenime.core.data.mapper.asCharacter
import com.lelestacia.lelenime.core.data.mapper.asCharacterDetail
import com.lelestacia.lelenime.core.data.mapper.asCharacterWithVoiceActorEntities
import com.lelestacia.lelenime.core.data.mapper.asNewEntity
import com.lelestacia.lelenime.core.data.mapper.asNewVoiceActor
import com.lelestacia.lelenime.core.database.animeStuff.entity.anime.AnimeCharacterReferenceEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterInformationEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterProfile
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterVoiceActorCrossRefEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.voiceActor.VoiceActorEntity
import com.lelestacia.lelenime.core.database.animeStuff.service.ICharacterDatabaseService
import com.lelestacia.lelenime.core.model.character.Character
import com.lelestacia.lelenime.core.model.character.CharacterDetail
import com.lelestacia.lelenime.core.network.model.character.CharacterDetailResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse
import com.lelestacia.lelenime.core.network.source.anime.IAnimeNetworkService
import com.lelestacia.lelenime.core.network.source.character.ICharacterNetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val animeNetworkService: IAnimeNetworkService,
    private val characterNetworkService: ICharacterNetworkService,
    private val characterDatabaseService: ICharacterDatabaseService,
    private val errorParser: JikanErrorParserUtil,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ICharacterRepository {

    /**
     * A function that returns a flow of [Resource] which emits a list of [Character] for a given anime ID.
     * This function first fetches the anime characters' data from the local database using the [ICharacterDatabaseService].
     * If the local data is empty or outdated, this function then fetches the data from the remote API using the [IAnimeNetworkService].
     * The fetched data is then stored in the local database.
     * The [Resource] emitted by the flow contains a [List] of [Character] entities.
     * @param animeID the ID of the anime for which the characters' data is required
     * @return a [Flow] of [Resource] of [List] of [Character]
     */
    override fun getAnimeCharactersByAnimeID(animeID: Int): Flow<Resource<List<Character>>> =
        flow<Resource<List<Character>>> {
            val animeCharactersCrossRef: List<AnimeCharacterReferenceEntity> =
                characterDatabaseService.getCharactersByAnimeID(animeID = animeID)
            Timber.d("Database Responded with ${animeCharactersCrossRef.size} character references for Anime ID $animeID")

            if (animeCharactersCrossRef.isEmpty()) {
                val apiResponse: List<CharacterResponse> =
                    animeNetworkService.getAnimeCharactersByAnimeID(animeID = animeID)
                Timber.d("API Responded with ${apiResponse.size} characters  for Anime ID $animeID")

                if (apiResponse.isEmpty()) {
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
                val apiResponse: List<CharacterResponse> = animeNetworkService
                    .getAnimeCharactersByAnimeID(animeID = animeID)

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
            emit(
                Resource.Error(
                    data = null,
                    message = errorParser(t)
                )
            )
        }.flowOn(ioDispatcher)

    override fun getCharacterDetailByID(characterID: Int): Flow<Resource<CharacterDetail>> =
        flow {
            var localCharacter: CharacterInformationEntity? =
                characterDatabaseService.getCharacterAdditionalInformationById(characterID)

            if (localCharacter != null) {
                val localCharacterDetail =
                    characterDatabaseService.getCharacterFullProfile(characterID = characterID)
                emit(Resource.Success(data = localCharacterDetail.asCharacterDetail()))
            }

            val oldestUpdate: Long =
                if (localCharacter == null) {
                    0
                } else {
                    (localCharacter.updatedAt ?: localCharacter.createdAt).time
                }

            val timeDifference = Date().time - oldestUpdate
            val isDataOutdated = {
                val differenceInHours = TimeUnit.MILLISECONDS.toHours(timeDifference).toInt()
                Timber.d("Difference in hours is $differenceInHours")
                differenceInHours >= 24
                true
            }

            val shouldFetchNetwork = localCharacter == null || isDataOutdated.invoke()
            if (shouldFetchNetwork) {
                emit(Resource.Loading)
                val apiResponse: CharacterDetailResponse = characterNetworkService
                    .getCharacterDetailByCharacterID(characterID)
                val newEntities = apiResponse.asNewEntity()

                localCharacter =
                    localCharacter?.copy(
                        characterNickNames = newEntities.characterNickNames,
                        characterFavorite = newEntities.characterFavorite,
                        characterInformation = newEntities.characterInformation,
                        updatedAt = Date()
                    ) ?: newEntities

                characterDatabaseService.insertOrUpdateAdditionalInformation(
                    characterInformationEntity = localCharacter
                )
            }

            val fullInformation =
                characterDatabaseService.getCharacterFullProfile(characterID = characterID)
            emit(Resource.Success(data = fullInformation.asCharacterDetail()))
        }.onStart {
            emit(Resource.Loading)
        }.catch { t ->
            Timber.w("Error happened in Character Repository: $t")

            val localCharacter: CharacterInformationEntity? =
                characterDatabaseService.getCharacterAdditionalInformationById(characterID)

            val localCharacterProfile: CharacterProfile? =
                if (localCharacter == null) {
                    null
                } else {
                    characterDatabaseService.getCharacterFullProfile(characterID = characterID)
                }

            /**
             * If a local character is null,
             * this means exception happened during a fresh pull network request,
             * but if it is existed,
             * then it means the exception was happened during update network request
             */

            emit(
                Resource.Error(
                    data = null,
                    message = errorParser(t)
                )
            )
        }.flowOn(ioDispatcher)
}
