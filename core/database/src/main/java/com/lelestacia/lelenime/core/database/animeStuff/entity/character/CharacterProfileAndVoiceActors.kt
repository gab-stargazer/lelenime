package com.lelestacia.lelenime.core.database.animeStuff.entity.character

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.lelestacia.lelenime.core.database.animeStuff.entity.voiceActor.VoiceActorEntity
import com.lelestacia.lelenime.core.database.animeStuff.util.DatabaseConstant.CHARACTER_ID
import com.lelestacia.lelenime.core.database.animeStuff.util.DatabaseConstant.VOICE_ACTOR_ID

data class CharacterProfileAndVoiceActors(
    @Embedded val characterEntity: CharacterEntity,

    @Relation(
        parentColumn = CHARACTER_ID,
        entityColumn = CHARACTER_ID
    )
    val characterInformationEntity: CharacterInformationEntity,

    @Relation(
        entity = VoiceActorEntity::class,
        parentColumn = CHARACTER_ID,
        entityColumn = VOICE_ACTOR_ID,
        associateBy = Junction(
            value = CharacterVoiceActorCrossRefEntity::class,
            parentColumn = CHARACTER_ID,
            entityColumn = VOICE_ACTOR_ID
        )
    )
    val voiceActorEntities: List<VoiceActorEntity>
)
