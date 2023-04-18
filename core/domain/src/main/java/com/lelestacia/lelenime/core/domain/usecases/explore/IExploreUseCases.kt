package com.lelestacia.lelenime.core.domain.usecases.explore

import androidx.paging.PagingData
import com.lelestacia.lelenime.core.model.Anime
import kotlinx.coroutines.flow.Flow

interface IExploreUseCases {
    suspend fun insertOrUpdateAnimeHistory(anime: Anime)
    fun getAiringAnime(): Flow<PagingData<Anime>>
    fun getUpcomingAnime(): Flow<PagingData<Anime>>
    fun getPopularAnime(): Flow<PagingData<Anime>>
    fun getAnimeSearch(
        searchQuery: String,
        type: String? = null,
        status: String? = null,
        rating: String? = null
    ): Flow<PagingData<Anime>>
}
