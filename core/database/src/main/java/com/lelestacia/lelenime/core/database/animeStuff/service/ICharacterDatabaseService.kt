package com.lelestacia.lelenime.core.database.animeStuff.service

import com.lelestacia.lelenime.core.database.animeStuff.entity.anime.AnimeCharacterReferenceEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterInformationEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterProfile
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterVoiceActorCrossRefEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.voiceActor.VoiceActorEntity

interface ICharacterDatabaseService {
    suspend fun insertCharactersAndVoiceActors(
        characters: List<CharacterEntity>,
        voiceActors: List<VoiceActorEntity>,
        animeCharactersCrossRef: List<AnimeCharacterReferenceEntity>,
        characterVoiceActorsCrossRef: List<CharacterVoiceActorCrossRefEntity>
    )
    suspend fun insertOrUpdateAdditionalInformation(characterInformationEntity: CharacterInformationEntity)
    suspend fun getCharactersByAnimeID(animeID: Int): List<AnimeCharacterReferenceEntity>
    suspend fun getCharactersByCharacterID(characterID: List<Int>): List<CharacterEntity>
    suspend fun getVoiceActorsByCharacterID(characterID: List<Int>): List<VoiceActorEntity>
    suspend fun getCharacterAdditionalInformationById(characterId: Int): CharacterInformationEntity?
    suspend fun getCharacterFullProfile(characterID: Int): CharacterProfile
    suspend fun updateCharactersAndVoiceActors(
        characters: Pair<List<CharacterEntity>, List<CharacterEntity>>,
        voiceActors: Pair<List<VoiceActorEntity>, List<VoiceActorEntity>>
    )
}
