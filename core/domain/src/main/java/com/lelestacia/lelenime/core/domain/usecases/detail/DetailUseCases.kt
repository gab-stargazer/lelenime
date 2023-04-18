package com.lelestacia.lelenime.core.domain.usecases.detail

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.data.repository.IAnimeRepository
import com.lelestacia.lelenime.core.model.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCases @Inject constructor(
    private val repository: IAnimeRepository
) : IDetailUseCases {

    override suspend fun updateAnimeFavoriteByAnimeID(animeID: Int) {
        repository.updateAnimeFavoriteByAnimeID(animeID = animeID)
    }

    override fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>> {
        return repository.getAnimeFromLocalDatabaseByAnimeID(animeID = animeID)
    }
}
