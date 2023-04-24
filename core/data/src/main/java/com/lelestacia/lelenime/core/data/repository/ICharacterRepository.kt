package com.lelestacia.lelenime.core.data.repository

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.character.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {

    /**
     * Returns a flow of [Resource] objects that contain a list of [Character] objects related to the given anime ID.
     * The function first attempts to retrieve the characters from the local database, and if no characters are found, it
     * queries the API to get the characters and inserts them into the local database. If characters are found in the local
     * database, the function will emit those characters to the flow immediately, but will also query the API to check if
     * the data is outdated. If the data is outdated, the function will update the local database with the latest data from
     * the API and emit the new characters to the flow. The function can emit [Resource.Loading] while waiting for data to be
     * retrieved, and will emit [Resource.Error] if an error occurs during retrieval or processing of the data.
     *
     * @param animeID The ID of the anime to retrieve the characters for.
     * @return A flow of [Resource] objects containing a list of [Character] objects.
     */
    fun getAnimeCharactersByAnimeID(animeID: Int): Flow<Resource<List<Character>>>
}