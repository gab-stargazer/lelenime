package com.lelestacia.lelenime.core.data.repository

import androidx.paging.PagingData
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {

    /**
     * Inserts or updates the [anime] history record in the local database.
     * @param anime the anime to insert or update
     */
    suspend fun insertOrUpdateAnimeHistory(anime: Anime)

    /**
     * Updates the favorite status of the anime with the given [animeID] in the local database.
     * @param animeID the ID of the anime to update
     */
    suspend fun updateAnimeFavoriteByAnimeID(animeID: Int)

    /**
     * Returns a [Flow] that emits paginated data of currently airing anime.
     *
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @return A [Flow] that emits paginated data of [Anime] objects for currently airing anime.
     *
     * @see Flow
     * @see PagingData
     * @see Anime
     */
    fun getAiringAnime(
        type: String? = null
    ): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] that emits paginated data of upcoming anime.
     *
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @return A [Flow] that emits paginated data of [Anime] objects for upcoming anime.
     *
     * @see Flow
     * @see PagingData
     * @see Anime
     */
    fun getUpcomingAnime(
        type: String? = null
    ): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] that emits paginated data of popular anime.
     *
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @param filter The optional filter parameter for the anime. If provided, it filters the anime based on the specified filter.
     *               The default is null, which means no specific filter is applied.
     * @param rating The optional rating parameter for the anime. If provided, it filters the anime based on the specified rating.
     *               The default is null, which means no specific rating filter is applied.
     * @return A [Flow] that emits paginated data of [Anime] objects for popular anime.
     *
     * @see Flow
     * @see PagingData
     * @see Anime
     */
    fun getPopularAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null
    ): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] that emits paginated data of anime based on the search query and optional filters.
     *
     * @param searchQuery The search query for finding anime by title or keywords.
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @param rating The optional rating parameter for the anime. If provided, it filters the anime based on the specified rating.
     *               The default is null, which means no specific rating filter is applied.
     * @param status The optional status parameter for the anime. If provided, it filters the anime based on the specified status.
     *               The default is null, which means no specific status filter is applied.
     * @param sort The optional sort parameter for the anime. If provided, it sorts the anime based on the specified criteria.
     *             The default is null, which means no specific sorting is applied.
     * @param genres The optional genres parameter for the anime. If provided, it filters the anime based on the specified genres.
     *               The default is null, which means no specific genres filter is applied.
     * @return A [Flow] that emits paginated data of [Anime] objects based on the search query and filters.
     *
     * @see Flow
     * @see PagingData
     * @see Anime
     */
    fun getAnimeSearch(
        searchQuery: String,
        type: String? = null,
        rating: String? = null,
        status: String? = null,
        sort: String? = null,
        genres: String? = null
    ): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] of [PagingData] for the user's anime history.
     */
    fun getAnimeHistory(): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] of [PagingData] for the user's favorite anime.
     */
    fun getAnimeFavorite(): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] of [Resource] for the anime with the given [animeID] from the local database.
     * @param animeID the ID of the anime to retrieve
     */
    fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>>

    fun parseThrowable(t: Throwable): String
}
