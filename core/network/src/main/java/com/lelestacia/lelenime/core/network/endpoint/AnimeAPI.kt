package com.lelestacia.lelenime.core.network.endpoint

import com.lelestacia.lelenime.core.network.model.GenericPaginationResponse
import com.lelestacia.lelenime.core.network.model.GenericResponse
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeAPI {

    /**
     * Retrieves the current season's anime data from the server.
     *
     * @param page The page number for pagination.
     * @return A GenericPaginationResponse containing the anime response for the current season.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("seasons/now")
    suspend fun getCurrentSeason(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves the upcoming season's anime data from the server.
     *
     * @param page The page number for pagination.
     * @return A GenericPaginationResponse containing the anime response for the upcoming season.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("seasons/upcoming")
    suspend fun getUpcomingSeason(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves the popular anime data from the server.
     *
     * @param page The page number for pagination.
     * @return A GenericPaginationResponse containing the popular anime response.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves popular anime data based on the provided page number and type.
     *
     * @param page The page number for pagination.
     * @param type The type of anime to retrieve (e.g., TV, Movie, OVA, etc.).
     * @return A GenericPaginationResponse containing the popular anime response for the given page and type.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("page") page: Int,
        @Query("type") type: String
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves popular anime data based on the provided page number, type, and filter.
     *
     * @param page The page number for pagination.
     * @param type The type of anime to retrieve (e.g., TV, Movie, OVA, etc.).
     * @param filter The filter to apply to the popular anime results.
     * @return A GenericPaginationResponse containing the popular anime response for the given page, type, and filter.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("page") page: Int,
        @Query("type") type: String,
        @Query("filter") filter: String
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves anime search results from the server based on the provided query.
     *
     * @param q The search query string.
     * @param page The page number for pagination.
     * @param sfw Determines whether the search results should be Safe for Work (SFW). Default is true.
     * @return A GenericPaginationResponse containing the search results for the provided query.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("sfw") sfw: Boolean = true
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves anime search results from the server based on the provided query and type.
     *
     * @param q The search query string.
     * @param type The type of anime to search for (e.g., TV, Movie, OVA, etc.).
     * @param page The page number for pagination.
     * @return A GenericPaginationResponse containing the search results for the provided query and type.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves anime search results from the server based on the provided query, type, status, and page.
     *
     * @param q The search query string.
     * @param type The type of anime to search for (e.g., TV, Movie, OVA, etc.).
     * @param status The status of the anime (e.g., airing, completed, upcoming, etc.).
     * @param page The page number for pagination.
     * @return A GenericPaginationResponse containing the search results for the provided query, type, status, and page.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("status") status: String,
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Retrieves anime search results from the server based on the provided query, type, status, rating, and page.
     *
     * @param q The search query string.
     * @param type The type of anime to search for (e.g., TV, Movie, OVA, etc.).
     * @param status The status of the anime (e.g., airing, completed, upcoming, etc.).
     * @param rating The rating of the anime (e.g., g, pg, pg_13, r17, r, rx, etc.).
     * @param page The page number for pagination.
     * @return A GenericPaginationResponse containing the search results for the provided query, type, status, rating, and page.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("status") status: String,
        @Query("rating") rating: String,
        @Query("page") page: Int
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
        @Path("id") id: Int
    ): GenericResponse<List<CharacterResponse>>
}
