package com.lelestacia.lelenime.core.domain.usecases.detail

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.Anime
import kotlinx.coroutines.flow.Flow

interface IDetailUseCases {
    suspend fun updateAnimeFavoriteByAnimeID(animeID: Int)
    fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>>
}