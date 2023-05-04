package com.lelestacia.lelenime.core.network.endpoint

import com.lelestacia.lelenime.core.network.model.GenericResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterAPI {

    @GET("characters/{id}/full")
    suspend fun getCharacterDetailByCharacterID(
        @Path("id") id: Int
    ): GenericResponse<CharacterDetailResponse>
}