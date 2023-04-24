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
     * Returns a [Flow] of [PagingData] for currently airing anime.
     */
    fun getAiringAnime(): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] of [PagingData] for upcoming anime.
     */
    fun getUpcomingAnime(): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] of [PagingData] for popular anime.
     */
    fun getPopularAnime(): Flow<PagingData<Anime>>

    /**
     * Returns a [Flow] of [PagingData] for searched anime based on the provided [searchQuery], [type], [status], and [rating].
     * @param searchQuery the query to search for anime
     * @param type the type of anime to search for
     * @param status the status of anime to search for
     * @param rating the rating of anime to search for
     */
    fun getAnimeSearch(
        searchQuery: String,
        type: String? = null,
        status: String? = null,
        rating: String? = null
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
}
