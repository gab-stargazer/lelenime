package com.lelestacia.lelenime.core.network.source.anime

import androidx.paging.PagingSource
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse

interface IAnimeNetworkService {

    /**
     * Returns a [PagingSource] that provides paginated data of currently airing anime.
     *
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @return A [PagingSource] that emits paginated data of [AnimeResponse] objects for currently airing anime.
     *
     * @see PagingSource
     * @see AnimeResponse
     */
    fun getAiringAnime(
        type: String? = null
    ): PagingSource<Int, AnimeResponse>

    /**
     * Returns a [PagingSource] that provides paginated data of upcoming anime.
     *
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @return A [PagingSource] that emits paginated data of [AnimeResponse] objects for upcoming anime.
     *
     * @see PagingSource
     * @see AnimeResponse
     */
    fun getUpcomingAnime(
        type: String? = null
    ): PagingSource<Int, AnimeResponse>

    /**
     * Returns a [PagingSource] that provides paginated data of popular anime.
     *
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @param filter The optional filter parameter for the anime. If provided, it filters the anime based on the specified filter.
     *               The default is null, which means no specific filter is applied.
     * @param rating The optional rating parameter for the anime. If provided, it filters the anime based on the specified rating.
     *               The default is null, which means no specific rating filter is applied.
     * @return A [PagingSource] that emits paginated data of [AnimeResponse] objects for popular anime.
     *
     * @see PagingSource
     * @see AnimeResponse
     */
    fun getPopularAnime(
        type: String? = null,
        filter:String? = null,
        rating: String? = null,
    ): PagingSource<Int, AnimeResponse>

    /**
     * Returns a [PagingSource] that provides paginated data of anime based on the search query and optional filters.
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
     *               The default is null, which means no specific genre filter is applied.
     * @return A [PagingSource] that emits paginated data of [AnimeResponse] objects based on the search query and filters.
     *
     * @see PagingSource
     * @see AnimeResponse
     */
    fun getAnimeSearch(
        searchQuery: String,
        type: String? = null,
        rating: String? = null,
        status: String? = null,
        sort: String? = null,
        genres: String? = null,
    ): PagingSource<Int, AnimeResponse>

    /**
     * Retrieves a list of [CharacterResponse] objects for the given anime ID.
     *
     * @param animeID The ID of the anime to retrieve character information for.
     * @return A list of [CharacterResponse] objects.
     */
    suspend fun getAnimeCharactersByAnimeID(animeID: Int): List<CharacterResponse>
}
