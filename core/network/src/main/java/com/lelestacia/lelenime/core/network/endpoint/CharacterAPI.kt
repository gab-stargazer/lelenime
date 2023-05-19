package com.lelestacia.lelenime.core.network.endpoint

import com.lelestacia.lelenime.core.network.model.GenericResponse
import com.lelestacia.lelenime.core.network.model.character.CharacterDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterAPI {

    /**
     * Retrieves detailed information about a specific character based on the provided character ID.
     *
     * @param id The ID of the character.
     * @return A GenericResponse containing the detailed information of the character for the given character ID.
     * @throws IOException if there is an issue with the network connection or with reading/writing data.
     * @throws HttpException if an HTTP response with a status code outside the range of 200-299 is received.
     * @throws UnknownHostException if the host specified in the URL cannot be resolved or if there is no internet connection.
     * @throws SocketTimeoutException if the connection timeout or read timeout expires before the operation completes.
     */
    @GET("characters/{id}/full")
    suspend fun getCharacterDetailByCharacterID(
        @Path("id") id: Int
    ): GenericResponse<CharacterDetailResponse>
}
