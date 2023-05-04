package com.lelestacia.lelenime.core.data.mapper

import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterInformationEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterProfile
import com.lelestacia.lelenime.core.database.animeStuff.entity.voiceActor.VoiceActorEntity
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse
import com.lelestacia.lelenime.core.model.character.Character
import com.lelestacia.lelenime.core.model.character.CharacterDetail
import com.lelestacia.lelenime.core.network.model.character.CharacterDetailResponse
import java.util.Date

fun CharacterResponse.asNewEntity(): CharacterEntity =
    CharacterEntity(
        characterID = characterData.malID,
        image = characterData.images.webp.imageUrl,
        name = characterData.name,
        role = role,
        favorite = favorites,
        createdAt = Date(),
        updatedAt = null
    )

fun CharacterResponse.asCharacterWithVoiceActorEntities(): Pair<Int, List<VoiceActorEntity>> {
    val characterID: Int = characterData.malID
    val voiceActorEntities: List<VoiceActorEntity> = voiceActors.map { networkVoiceActor ->
        VoiceActorEntity(
            voiceActorID = networkVoiceActor.person.malID,
            image = networkVoiceActor.person.image.jpg.imageUrl,
            name = networkVoiceActor.person.name,
            language = networkVoiceActor.language,
            created_at = Date(),
            updated_at = null
        )
    }
    return Pair(characterID, voiceActorEntities)
}

fun CharacterResponse.asNewVoiceActor(): List<VoiceActorEntity> {
    return voiceActors.map { networkVoiceActor ->
        VoiceActorEntity(
            voiceActorID = networkVoiceActor.person.malID,
            image = networkVoiceActor.person.image.jpg.imageUrl,
            name = networkVoiceActor.person.name,
            language = networkVoiceActor.language,
            created_at = Date(),
            updated_at = null
        )
    }
}

fun CharacterEntity.asCharacter(): Character =
    Character(
        malID = characterID,
        images = image,
        name = name,
        role = name,
        favorites = favorite
    )

fun CharacterDetailResponse.asNewEntity(): CharacterInformationEntity =
    CharacterInformationEntity(
        characterID = characterMalID,
        characterKanjiName = characterKanjiName.orEmpty(),
        characterNickNames = characterNickNames,
        characterFavorite = characterFavoriteCount,
        characterInformation = characterInformation.orEmpty(),
        createdAt = Date(),
        updatedAt = null
    )

fun CharacterProfile.asCharacterDetail(): CharacterDetail =
    CharacterDetail(
        characterID = character.characterID,
        name = character.name,
        characterKanjiName = additionalInformation.characterKanjiName,
        characterNickNames = additionalInformation.characterNickNames,
        images = character.image,
        role = character.role,
        favoriteBy = character.favorite,
        characterInformation = additionalInformation.characterInformation
    )