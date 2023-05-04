package com.lelestacia.lelenime.core.domain.usecases.detailAnime

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.data.repository.IAnimeRepository
import com.lelestacia.lelenime.core.data.repository.ICharacterRepository
import com.lelestacia.lelenime.core.data.repository.IUserPreferencesRepository
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailAnimeUseCases @Inject constructor(
    private val animeRepository: IAnimeRepository,
    private val characterRepository: ICharacterRepository,
    private val preferencesRepository: IUserPreferencesRepository
) : IDetailAnimeUseCases {

    override suspend fun updateAnimeFavoriteByAnimeID(animeID: Int) {
        animeRepository.updateAnimeFavoriteByAnimeID(animeID = animeID)
    }

    override fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>> {
        return animeRepository.getAnimeFromLocalDatabaseByAnimeID(animeID = animeID)
    }

    override suspend fun isAnimeCharactersFetchedBefore(animeID: Int): Flow<Boolean> {
        return preferencesRepository.isAnimeCharactersFetchedBefore(animeID = animeID)
    }

    override suspend fun updateAnimeCharactersFetchedBefore(animeID: Int) {
        preferencesRepository.updateAnimeCharactersFetchedBefore(animeID = animeID)
    }

    override fun getCharacterByAnimeID(animeID: Int): Flow<Resource<List<Character>>> {
        return characterRepository.getAnimeCharactersByAnimeID(animeID = animeID)
    }
}
