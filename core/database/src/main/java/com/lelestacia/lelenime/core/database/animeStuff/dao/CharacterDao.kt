package com.lelestacia.lelenime.core.database.animeStuff.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterInformationEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterProfile
import com.lelestacia.lelenime.core.database.animeStuff.entity.character.CharacterProfileAndVoiceActors

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character_table WHERE character_id IN (:characterID)")
    fun getCharactersByCharacterID(characterID: List<Int>): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAdditionalInformation(characterInformationEntity: CharacterInformationEntity)

    @Query("SELECT * FROM character_information_table WHERE character_id = :characterID")
    fun getCharacterAdditionalInformationById(characterID: Int): CharacterInformationEntity?

    @Transaction
    @Query("SELECT * FROM character_table WHERE character_id =:characterID")
    fun getCharacterFullProfile(characterID: Int): CharacterProfile

    @Transaction
    @Query("SELECT * FROM character_table WHERE character_id =:characterID")
    fun getCharacterProfileAndVoiceActors(characterID: Int): CharacterProfileAndVoiceActors

    @Update
    suspend fun updateCharacters(characters: List<CharacterEntity>)
}
