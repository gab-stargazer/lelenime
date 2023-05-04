package com.lelestacia.lelenime.core.domain.usecases.detailAnime

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.Character
import kotlinx.coroutines.flow.Flow

interface IDetailAnimeUseCases {
    suspend fun updateAnimeFavoriteByAnimeID(animeID: Int)
    fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>>
    suspend fun isAnimeCharactersFetchedBefore(animeID: Int): Flow<Boolean>
    suspend fun updateAnimeCharactersFetchedBefore(animeID: Int)
    fun getCharacterByAnimeID(animeID: Int): Flow<Resource<List<Character>>>
}
