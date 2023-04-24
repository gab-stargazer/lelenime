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
     * Get the current season of anime.
     *
     * @param page Page number to retrieve.
     * @return [GenericPaginationResponse] of [AnimeResponse].
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
     */
    @GET("seasons/now")
    suspend fun getCurrentSeason(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Get the upcoming season of anime.
     *
     * @param page Page number to retrieve.
     * @return [GenericPaginationResponse] of [AnimeResponse].
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
     */
    @GET("seasons/upcoming")
    suspend fun getUpcomingSeason(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Get the list of popular anime.
     *
     * @param page Page number to retrieve.
     * @return [GenericPaginationResponse] of [AnimeResponse].
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
     */
    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Search for anime based on the provided query.
     *
     * @param q Search query.
     * @param page Page number to retrieve.
     * @param sfw Whether to include NSFW content or not. Default is true.
     * @return [GenericPaginationResponse] of [AnimeResponse].
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("sfw") sfw: Boolean = true
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Search for anime based on the provided query and type.
     *
     * @param q Search query.
     * @param type Type of anime (TV, Movie, etc.).
     * @param page Page number to retrieve.
     * @return [GenericPaginationResponse] of [AnimeResponse].
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Search for anime based on the provided query, type, and status.
     *
     * @param q Search query.
     * @param type Type of anime (TV, Movie, etc.).
     * @param status Status of the anime (Airing, Finished, etc.).
     * @param page Page number to retrieve.
     * @return [GenericPaginationResponse] of [AnimeResponse].
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
     */
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("status") status: String,
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    /**
     * Search for anime based on the provided query, type, status, and rating.
     *
     * @param q Search query.
     * @param type Type of anime (TV, Movie, etc.).
     * @param status Status of the anime (Airing, Finished, etc.).
     * @param rating Age rating of the anime (G, PG, etc.).
     * @param page Page number to retrieve.
     * @return [GenericPaginationResponse] of [AnimeResponse].
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
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
     * Retrieves a list of characters for a specific anime by providing the anime ID.
     *
     * @param id The ID of the anime to retrieve characters for.
     * @return A [GenericResponse] object containing a list of [CharacterResponse] objects.
     * @throws [HttpException] if the request is not successful (e.g. server error, unauthorized).
     * @throws [IOException] if a problem occurs while parsing the response.
     */
    @GET("anime/{id}/characters")
    suspend fun getAnimeCharactersByAnimeID(
        @Path("{id}") id: Int
    ): GenericResponse<List<CharacterResponse>>
}
