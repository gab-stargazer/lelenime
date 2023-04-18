package com.lelestacia.lelenime.core.database.animeStuff.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lelestacia.lelenime.core.database.animeStuff.entity.voiceActor.VoiceActorEntity

@Dao
interface VoiceActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceVoiceActors(voiceActors: List<VoiceActorEntity>)

    @Query("SELECT * FROM voice_actor_table WHERE voice_actor_id IN (:voiceActorID)")
    fun getVoiceActorByVoiceActorID(voiceActorID: List<Int>): List<VoiceActorEntity>

    @Update
    suspend fun updateVoiceActors(voiceActors: List<VoiceActorEntity>)
}
