package com.lelestacia.lelenime.core.database.animeStuff.service

import com.lelestacia.lelenime.core.database.animeStuff.dao.AnimeCharacterCrossRefDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.CharacterDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.VoiceActorDao
import com.lelestacia.lelenime.core.database.animeStuff.entity.anime.AnimeCharacterReferenceEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterInformationEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterProfile
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterVoiceActorCrossRefEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.voiceActor.VoiceActorEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class CharacterDatabaseService(
    private val animeCharactersCrossRefDao: AnimeCharacterCrossRefDao,
    private val characterVoiceActorsCrossRefDao: CharacterVoiceActorCrossRefDao,
    private val characterDao: CharacterDao,
    private val voiceActorDao: VoiceActorDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ICharacterDatabaseService {

    override suspend fun insertCharactersAndVoiceActors(
        characters: List<CharacterEntity>,
        voiceActors: List<VoiceActorEntity>,
        animeCharactersCrossRef: List<AnimeCharacterReferenceEntity>,
        characterVoiceActorsCrossRef: List<CharacterVoiceActorCrossRefEntity>
    ) {
        withContext(ioDispatcher) {
            characterDao.insertOrReplaceCharacters(characters = characters)
            voiceActorDao.insertOrReplaceVoiceActors(voiceActors = voiceActors)
            animeCharactersCrossRefDao.insertCrossRef(crossRef = animeCharactersCrossRef)
            characterVoiceActorsCrossRefDao.insertOrReplaceCrossRef(crossRef = characterVoiceActorsCrossRef)
        }
    }

    override suspend fun insertOrUpdateAdditionalInformation(characterInformationEntity: CharacterInformationEntity) {
        withContext(ioDispatcher) {
            characterDao.insertOrUpdateAdditionalInformation(characterInformationEntity = characterInformationEntity)
        }
    }

    override suspend fun getCharactersByAnimeID(animeID: Int): List<AnimeCharacterReferenceEntity> {
        return withContext(ioDispatcher) {
            animeCharactersCrossRefDao.getCharactersByAnimeID(animeID = animeID)
        }
    }

    override suspend fun getCharactersByCharacterID(characterID: List<Int>): List<CharacterEntity> {
        return withContext(ioDispatcher) {
            characterDao.getCharactersByCharacterID(characterID = characterID)
        }
    }

    override suspend fun getVoiceActorsByCharacterID(characterID: List<Int>): List<VoiceActorEntity> {
        return withContext(ioDispatcher) {
            val crossRef = characterID.map {
                characterVoiceActorsCrossRefDao.getVoiceActorsByCharacterID(characterID = it)
            }
            crossRef.map { entities ->
                val voiceActorsID = entities.map { it.voiceActorID }
                voiceActorDao.getVoiceActorByVoiceActorID(voiceActorID = voiceActorsID)
            }.flatten()
        }
    }

    override suspend fun getCharacterAdditionalInformationById(characterId: Int): CharacterInformationEntity? {
        return withContext(ioDispatcher) {
            characterDao.getCharacterAdditionalInformationById(characterID = characterId)
        }
    }

    override suspend fun getCharacterFullProfile(characterID: Int): CharacterProfile {
        return withContext(ioDispatcher) {
            characterDao.getCharacterFullProfile(characterID = characterID)
        }
    }

    override suspend fun updateCharactersAndVoiceActors(
        characters: Pair<List<CharacterEntity>, List<CharacterEntity>>,
        voiceActors: Pair<List<VoiceActorEntity>, List<VoiceActorEntity>>
    ) {
        withContext(ioDispatcher) {
            val oldCharacters = characters.first
            val newCharacters = characters.second
            val updatedCharacters = oldCharacters.mapIndexed { index, oldCharacter ->
                val newCharacter = newCharacters[index]
                oldCharacter.copy(
                    image = newCharacter.image,
                    role = newCharacter.role,
                    favorite = newCharacter.favorite,
                    updatedAt = Date()
                )
            }

            val oldVoiceActors = voiceActors.first
            val newVoiceActors = voiceActors.second
            val updatedVoiceActors = oldVoiceActors.mapIndexed { index, oldVoiceActor ->
                val newVoiceActor = newVoiceActors[index]
                oldVoiceActor.copy(
                    image = newVoiceActor.image,
                    language = newVoiceActor.language,
                    updated_at = Date()
                )
            }

            characterDao.updateCharacters(characters = updatedCharacters)
            voiceActorDao.updateVoiceActors(voiceActors = updatedVoiceActors)
        }
    }
}
