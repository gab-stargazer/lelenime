package com.lelestacia.lelenime.core.data.repository

import androidx.paging.PagingData
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    suspend fun insertOrUpdateAnimeHistory(anime: Anime)
    suspend fun updateAnimeFavoriteByAnimeID(animeID: Int)
    fun getAiringAnime(): Flow<PagingData<Anime>>
    fun getUpcomingAnime(): Flow<PagingData<Anime>>
    fun getPopularAnime(): Flow<PagingData<Anime>>
    fun getAnimeSearch(
        searchQuery: String,
        type: String? = null,
        status: String? = null,
        rating: String? = null
    ): Flow<PagingData<Anime>>
    fun getAnimeHistory(): Flow<PagingData<Anime>>
    fun getAnimeFavorite(): Flow<PagingData<Anime>>
    fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>>
}
