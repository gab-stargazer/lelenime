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

    override fun getAiringAnime(type: String?): Flow<PagingData<Anime>> {
        return repository.getAiringAnime(type = type)
    }

    override fun getUpcomingAnime(type: String?): Flow<PagingData<Anime>> {
        return repository.getUpcomingAnime(type = type)
    }

    override fun getPopularAnime(
        type: String?,
        filter: String?,
        rating: String?
    ): Flow<PagingData<Anime>> {
        return repository.getPopularAnime(
            type = type,
            filter = filter,
            rating = rating
        )
    }

    override fun getAnimeSearch(
        searchQuery: String,
        type: String?,
        rating: String?,
        status: String?,
        sort: String?,
        genres: String?
    ): Flow<PagingData<Anime>> {
        return repository.getAnimeSearch(
            searchQuery = searchQuery,
            type = type,
            rating = rating,
            status = status,
            sort = sort,
            genres = genres
        )
    }

    override fun parseThrowable(t: Throwable): String {
        return repository.parseThrowable(t)
    }
}
