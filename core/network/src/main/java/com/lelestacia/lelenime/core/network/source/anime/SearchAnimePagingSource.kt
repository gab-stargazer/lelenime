package com.lelestacia.lelenime.core.network.source.anime

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.lelenime.core.network.endpoint.AnimeAPI
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import kotlinx.coroutines.delay

class SearchAnimePagingSource(
    private val animeAPI: AnimeAPI,
    private val searchQuery: String,
    private val type: String? = null,
    private val status: String? = null,
    private val rating: String? = null
) : PagingSource<Int, AnimeResponse>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> {
        return try {
            val currentPage = params.key ?: 1
            val apiResponse =
                when {
                    type != null && status != null && rating != null -> {
                        animeAPI.getAnimeSearch(
                            q = searchQuery,
                            type = type,
                            status = status,
                            rating = rating,
                            page = currentPage
                        )
                    }

                    type != null && status != null -> {
                        animeAPI.getAnimeSearch(
                            q = searchQuery,
                            type = type,
                            status = status,
                            page = currentPage
                        )
                    }

                    type != null -> {
                        animeAPI.getAnimeSearch(
                            q = searchQuery,
                            type = type,
                            page = currentPage
                        )
                    }

                    else -> {
                        animeAPI.getAnimeSearch(
                            q = searchQuery,
                            page = currentPage
                        )
                    }
                }
            delay(
                if (currentPage == 1) {
                    0
                } else {
                    500
                }
            )
            val previousPage =
                if (currentPage == 1) {
                    null
                } else {
                    currentPage - 1
                }
            val nextPage =
                if (apiResponse.pagination.hasNextPage) {
                    currentPage + 1
                } else {
                    null
                }
            LoadResult.Page(
                data = apiResponse.data,
                prevKey = previousPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
