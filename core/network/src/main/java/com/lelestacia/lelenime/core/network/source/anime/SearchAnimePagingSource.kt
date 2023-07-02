package com.lelestacia.lelenime.core.network.source.anime

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.lelenime.core.network.endpoint.AnimeAPI
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import kotlinx.coroutines.delay
import timber.log.Timber

/**
 * A `PagingSource` implementation for loading paginated anime search results based on various search parameters.
 *
 * @param animeAPI The AnimeAPI instance used to make API calls for retrieving anime search results.
 * @param searchQuery The search query used to filter the anime data based on the provided query.
 * @param type The optional type of anime content to filter by. If provided, it filters the anime data based on the specified type.
 *             Default is null, which means no specific type filter is applied.
 * @param rating The optional rating of anime content to filter by. If provided, it filters the anime data based on the specified rating.
 *               Default is null, which means no specific rating filter is applied.
 * @param status The optional status of anime content to filter by. If provided, it filters the anime data based on the specified status.
 *               Default is null, which means no specific status filter is applied.
 * @param sort The optional sorting criteria for the anime data. If provided, it sorts the anime data based on the specified criteria.
 *             Default is null, which means no specific sorting is applied.
 * @param genres The optional genres of anime content to filter by. If provided, it filters the anime data based on the specified genres.
 *               Default is null, which means no specific genre filter is applied.
 *
 * @see PagingSource
 * @see AnimeResponse
 */
class SearchAnimePagingSource(
    private val animeAPI: AnimeAPI,
    private val searchQuery: String,
    private val type: String? = null,
    private val rating: String? = null,
    private val status: String? = null,
    private val sort: String? = null,
    private val genres: String? = null,
) : PagingSource<Int, AnimeResponse>() {

    /**
     * Retrieves the refresh key for the current state of the `PagingSource`.
     * The refresh key is used to determine the starting page when the data is reloaded.
     *
     * @param state The current `PagingState` that represents the current state of the data.
     * @return The anchor position as the refresh key, which can be used to identify the starting page for the data reload.
     */
    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? {
        return state.anchorPosition
    }

    /**
     * Loads the paginated anime search results based on the provided [params] and search parameters.
     *
     * This function is called by the `PagingSource` when it needs to load data for a specific page.
     *
     * @param params The `LoadParams` object that contains information about the load operation,
     *               such as the key representing the requested page.
     * @return A `LoadResult` that contains the paginated list of [AnimeResponse] objects for the current page,
     *         along with the previous and next page keys if available.
     *         If an error occurs during the data retrieval process, the `LoadResult.Error` instance is returned.
     *
     * @see PagingSource.LoadParams
     * @see PagingSource.LoadResult
     * @see AnimeResponse
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> {
        return try {
            val currentPage = params.key ?: 1
            val apiResponse = animeAPI.getAnimeSearch(
                q = searchQuery,
                type = type,
                rating = rating,
                status = status,
                sortBy = sort,
                genre = genres,
                page = currentPage
            )

            // Simulate a delay in loading data based on the current page number.
            delay(
                if (currentPage == 1) {
                    0
                } else {
                    500
                }
            )

            // Calculate the previous and next page keys based on the API response.
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

            // Create and return the LoadResult.Page instance with the retrieved data and pagination keys.
            LoadResult.Page(
                data = apiResponse.data,
                prevKey = previousPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            // In case of an error,log the error using Timber and return the LoadResult.Error instance.
            Timber.e(e.stackTraceToString())
            LoadResult.Error(e)
        }
    }
}
