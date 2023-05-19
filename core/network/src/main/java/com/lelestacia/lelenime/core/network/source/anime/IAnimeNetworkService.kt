package com.lelestacia.lelenime.core.network.source.anime

import androidx.paging.PagingSource
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse

interface IAnimeNetworkService {

    /**
     * Retrieves a [PagingSource] for currently airing anime.
     *
     * @return A [PagingSource] that loads pages of [AnimeResponse] objects.
     */
    fun getAiringAnime(): PagingSource<Int, AnimeResponse>

    /**
     * Retrieves a [PagingSource] for upcoming anime.
     *
     * @return A [PagingSource] that loads pages of [AnimeResponse] objects.
     */
    fun getUpcomingAnime(): PagingSource<Int, AnimeResponse>

    /**
     * Retrieves a [PagingSource] for popular anime.
     *
     * @return A [PagingSource] that loads pages of [AnimeResponse] objects.
     */
    fun getPopularAnime(
        type: String? = null,
        status: String? = null
    ): PagingSource<Int, AnimeResponse>

    /**
     * Retrieves a [PagingSource] for anime search results based on the given query and filters.
     *
     * @param searchQuery The search query to use.
     * @param type The type of anime to filter by (e.g. TV, Movie, etc.)
     * @param status The status of the anime to filter by (e.g. Airing, Finished Airing, etc.)
     * @param rating The minimum rating of the anime to filter by (e.g. G, PG-13, R, etc.)
     * @return A [PagingSource] that loads pages of [AnimeResponse] objects.
     */
    fun getAnimeSearch(
        searchQuery: String,
        type: String? = null,
        status: String? = null,
        rating: String? = null
    ): PagingSource<Int, AnimeResponse>

    /**
     * Retrieves a list of [CharacterResponse] objects for the given anime ID.
     *
     * @param animeID The ID of the anime to retrieve character information for.
     * @return A list of [CharacterResponse] objects.
     */
    suspend fun getAnimeCharactersByAnimeID(animeID: Int): List<CharacterResponse>
}
