package com.lelestacia.lelenime.core.database.animeStuff.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lelestacia.lelenime.core.database.animeStuff.entity.anime.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceAnime(anime: AnimeEntity)

    @Query("SELECT * FROM anime_table ORDER BY last_viewed DESC")
    fun getAnimeHistory(): PagingSource<Int, AnimeEntity>

    @Query("SELECT * FROM anime_table WHERE favorite = 1")
    fun getAnimeFavorite(): PagingSource<Int, AnimeEntity>

    @Query("SELECT * FROM anime_table WHERE anime_id =:animeID")
    fun getAnimeByAnimeID(animeID: Int): AnimeEntity?

    @Query("SELECT * FROM anime_table WHERE anime_id =:animeID")
    fun getNewestAnimeDataByAnimeID(animeID: Int): Flow<AnimeEntity>

    @Update
    suspend fun updateAnime(anime: AnimeEntity)
}
