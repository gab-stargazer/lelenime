package com.lelestacia.lelenime.core.network.endpoint

import com.lelestacia.lelenime.core.network.model.GenericPaginationResponse
import com.lelestacia.lelenime.core.network.model.GenericResponse
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeAPI {
    @GET("seasons/now")
    suspend fun getCurrentSeason(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    @GET("seasons/upcoming")
    suspend fun getUpcomingSeason(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("sfw") sfw: Boolean = true
    ): GenericPaginationResponse<AnimeResponse>

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("status")status: String,
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("status")status: String,
        @Query("rating")rating: String,
        @Query("page") page: Int
    ): GenericPaginationResponse<AnimeResponse>

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharactersByAnimeID(
        @Path("{id}") id: Int
    ): GenericResponse<List<CharacterResponse>>
}
