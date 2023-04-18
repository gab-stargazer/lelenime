package com.lelestacia.lelenime.core.database.animeStuff.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.lelenime.core.database.animeStuff.entity.anime.AnimeCharacterReferenceEntity

@Dao
interface AnimeCharacterCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: List<AnimeCharacterReferenceEntity>)

    @Query("SELECT * FROM anime_characters_reference_table WHERE anime_id =:animeID")
    fun getCharactersByAnimeID(animeID: Int): List<AnimeCharacterReferenceEntity>
}
