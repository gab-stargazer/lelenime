package com.lelestacia.lelenime.core.database.animeStuff.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lelestacia.lelenime.core.database.animeStuff.entity.episode.EpisodeEntity

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateEpisode(episodes: List<EpisodeEntity>)

    @Query("SELECT * FROM episode_table WHERE anime_id = :animeID ORDER BY episode_id ASC")
    fun getEpisodeByAnimeID(animeID: Int): List<EpisodeEntity>

    @Update
    suspend fun updateEpisodes(episodes: List<EpisodeEntity>)
}
