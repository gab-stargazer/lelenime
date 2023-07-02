package com.lelestacia.lelenime.core.network.source.anime

import androidx.paging.PagingSource
import com.lelestacia.lelenime.core.network.endpoint.AnimeAPI
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse
import javax.inject.Inject

class AnimeNetworkService @Inject constructor(
    private val animeAPI: AnimeAPI
) : IAnimeNetworkService {

    override fun getAiringAnime(type: String?): PagingSource<Int, AnimeResponse> {
        return AiringAnimePagingSource(
            animeAPI = animeAPI,
            type = type
        )
    }

    override fun getUpcomingAnime(type: String?): PagingSource<Int, AnimeResponse> {
        return UpcomingAnimePagingSource(
            animeAPI = animeAPI,
            type = type
        )
    }

    override fun getPopularAnime(
        type: String?,
        filter: String?,
        rating: String?
    ): PagingSource<Int, AnimeResponse> {
        return PopularAnimePagingSource(
            animeAPI = animeAPI,
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
    ): PagingSource<Int, AnimeResponse> {
        return SearchAnimePagingSource(
            animeAPI = animeAPI,
            searchQuery = searchQuery,
            type = type,
            rating = rating,
            status = status,
            sort = sort,
            genres = genres
        )
    }

    override suspend fun getAnimeCharactersByAnimeID(animeID: Int): List<CharacterResponse> {
        return animeAPI.getAnimeCharactersByAnimeID(id = animeID).data
    }
}
