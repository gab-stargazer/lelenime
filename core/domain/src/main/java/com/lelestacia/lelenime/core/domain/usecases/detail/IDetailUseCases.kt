package com.lelestacia.lelenime.core.domain.usecases.detail

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.Character
import kotlinx.coroutines.flow.Flow

interface IDetailUseCases {
    suspend fun updateAnimeFavoriteByAnimeID(animeID: Int)
    fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>>
    fun getCharacterByAnimeID(animeID: Int): Flow<Resource<List<Character>>>
}
