package com.lelestacia.lelenime.core.network.endpoint

import com.lelestacia.lelenime.core.network.model.GenericPaginationResponse
import com.lelestacia.lelenime.core.network.model.GenericResponse
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse
import retrofit2.HttpException
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface AnimeAPI {

    /**
     * Sends a GET request to the "seasons/now" endpoint of the AnimeAPI to retrieve the anime airing in the current season.
     *
     * @param type The optional filter parameter for the anime. If provided, it filters the anime based on the specified filter.
     *               The default is null, which means no specific filter is applied.
     * @param page The page number of the search results to be retrieved.
     * @param sfw A boolean flag indicating whether to include Safe For Work (SFW) content. The default is true, which means SFW content is included.
     * @param limit The number of anime items to be retrieved per page. The default is 24, which means 24 anime items per page are retrieved.
     * @return A [GenericPaginationResponse] containing the paginated list of [AnimeResponse] objects airing in the current season.
     *
     * @see GET
     * @see AnimeAPI
     * @see GenericPaginationResponse
     * @see AnimeResponse
     */
    @GET("seasons/now")
    suspend fun getCurrentSeason(
        @Query("filter") type: String?,
        @Query("page") page: Int,
        @Query("sfw") sfw: Boolean = true,
        @Query("limit") limit: Int = 24,
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Sends a GET request to the "seasons/upcoming" endpoint of the AnimeAPI to retrieve upcoming season anime data.
     *
     * @param type The optional filter parameter for the anime content type. If provided, it filters the upcoming season anime data
     *             based on the specified content type. Default is null, which means no specific type filter is applied.
     * @param page The page number of the upcoming season anime data to be retrieved.
     * @param sfw A boolean flag indicating whether to include Safe For Work (SFW) content. Default is true, which means SFW content is included.
     * @param limit The number of anime items to be retrieved per page. Default is 24, which means 24 anime items per page are retrieved.
     * @return A [GenericPaginationResponse] containing the paginated list of [AnimeResponse] objects for the upcoming season.
     *
     * @see GET
     * @see AnimeAPI
     * @see GenericPaginationResponse
     * @see AnimeResponse
     */
    @GET("seasons/upcoming")
    suspend fun getUpcomingSeason(
        @Query("filter") type: String? = null,
        @Query("page") page: Int,
        @Query("sfw") sfw: Boolean = true,
        @Query("limit") limit: Int = 24,
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Sends a GET request to the "top/anime" endpoint of the AnimeAPI to retrieve popular anime.
     *
     * @param type The optional type parameter for the anime. If provided, it filters the anime based on the specified type.
     *             The default is null, which means no specific type filter is applied.
     * @param filter The optional filter parameter for the anime. If provided, it filters the anime based on the specified filter.
     *               The default is null, which means no specific filter is applied.
     * @param rating The optional rating parameter for the anime. If provided, it filters the anime based on the specified rating.
     *               The default is null, which means no specific rating filter is applied.
     * @param page The page number of the search results to be retrieved.
     * @param sfw A boolean flag indicating whether to include Safe For Work (SFW) content. The default is true, which means SFW content is included.
     * @param limit The number of anime items to be retrieved per page. The default is 24, which means 24 anime items per page are retrieved.
     * @return A [GenericPaginationResponse] containing the paginated list of [AnimeResponse] objects for popular anime.
     *
     * @see GET
     * @see AnimeAPI
     * @see GenericPaginationResponse
     * @see AnimeResponse
     */
    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("type") type: String?,
        @Query("filter") filter: String?,
        @Query("rating") rating: String?,
        @Query("page") page: Int,
        @Query("sfw") sfw: Boolean = true,
        @Query("limit") limit: Int = 24,
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Sends a GET request to the "anime" endpoint of the AnimeAPI to search for anime data based on various parameters.
     *
     * @param q The search query parameter used to search for anime titles or related information.
     * @param type The optional filter parameter for the anime content type. If provided, it filters the search results
     *             based on the specified content type. Default is null, which means no specific type filter is applied.
     * @param rating The optional filter parameter for the anime rating. If provided, it filters the search results
     *               based on the specified rating. Default is null, which means no specific rating filter is applied.
     * @param status The optional filter parameter for the anime status. If provided, it filters the search results
     *               based on the specified status. Default is null, which means no specific status filter is applied.
     * @param sortBy The optional parameter to specify the sorting order of the search results. If provided, it sorts the search results
     *               based on the specified field. Default is null, which means no specific sorting is applied.
     * @param genre The optional filter parameter for the anime genre. If provided, it filters the search results
     *              based on the specified genre. Default is null, which means no specific genre filter is applied.
     * @param page The page number of the search results to be retrieved.
     * @param sfw A boolean flag indicating whether to include Safe For Work (SFW) content. Default is true, which means SFW content is included.
     * @param limit The number of anime items to be retrieved per page. Default is 24, which means 24 anime items per page are retrieved.
     * @return A [GenericPaginationResponse] containing the paginated list of [AnimeResponse] objects matching the search criteria.
     *
     * @see GET
     * @see AnimeAPI
     * @see GenericPaginationResponse
     * @see AnimeResponse
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String?,
        @Query("rating") rating: String?,
        @Query("status") status: String?,
        @Query("sort") sortBy: String?,
        @Query("genres") genre: String?,
        @Query("page") page: Int,
        @Query("sfw") sfw: Boolean = true,
        @Query("limit") limit: Int = 24,
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves the list of characters for a specific anime based on the provided anime ID.
     *
     * @param id The ID of the anime.
     * @return A GenericResponse containing a list of character responses for the given anime ID.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("anime/{id}/characters")
    suspend fun getAnimeCharactersByAnimeID(
        @Path("id") id: Int,
    ): GenericResponse<List<CharacterResponse>>
}
