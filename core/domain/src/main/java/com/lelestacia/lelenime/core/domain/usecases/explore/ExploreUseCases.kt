package com.lelestacia.lelenime.core.domain.usecases.explore

import androidx.paging.PagingData
import com.lelestacia.lelenime.core.data.repository.IAnimeRepository
import com.lelestacia.lelenime.core.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExploreUseCases @Inject constructor(
    private val repository: IAnimeRepository
) : IExploreUseCases {

    override suspend fun insertOrUpdateAnimeHistory(anime: Anime) {
        repository.insertOrUpdateAnimeHistory(anime = anime)
    }

    override fun getAiringAnime(): Flow<PagingData<Anime>> {
        return repository.getAiringAnime()
    }

    override fun getUpcomingAnime(type: String?): Flow<PagingData<Anime>> {
        return repository.getUpcomingAnime(type = type)
    }

    override fun getPopularAnime(
        type: String?,
        status: String?
    ): Flow<PagingData<Anime>> {
        return repository.getPopularAnime(
            type = type,
            status = status
        )
    }

    override fun getAnimeSearch(
        searchQuery: String,
        type: String?,
        status: String?,
        rating: String?
    ): Flow<PagingData<Anime>> {
        return repository.getAnimeSearch(
            searchQuery = searchQuery,
            type = type,
            status = status,
            rating = rating
        )
    }

    override fun parseThrowable(t: Throwable): String {
        return repository.parseThrowable(t)
    }
}
