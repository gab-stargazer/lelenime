package com.lelestacia.lelenime.feature.detail.screen.detailAnime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.domain.usecases.detailAnime.IDetailAnimeUseCases
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailAnimeViewModel @Inject constructor(
    private val useCases: IDetailAnimeUseCases
) : ViewModel() {

    private val _anime: MutableStateFlow<Resource<Anime>> = MutableStateFlow(Resource.None)
    val anime: StateFlow<Resource<Anime>> get() = _anime.asStateFlow()

    private val _characters: MutableStateFlow<Resource<List<Character>>> =
        MutableStateFlow(Resource.None)
    val characters: StateFlow<Resource<List<Character>>> get() = _characters.asStateFlow()

    fun initiateView(animeID: Int) = viewModelScope.launch {
        if (_anime.value is Resource.None || _anime.value is Resource.Error) {
            getAnimeByAnimeID(animeID = animeID)
        }

        val isAnimeFetchedBefore: Boolean = useCases
            .isAnimeCharactersFetchedBefore(animeID = animeID)
            .first()
        if (_characters.value is Resource.None && isAnimeFetchedBefore) {
            getCharactersByAnimeID(animeID = animeID)
        }
    }

    private fun getAnimeByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.getAnimeFromLocalDatabaseByAnimeID(animeID = animeID).collectLatest { resource ->
            _anime.update { resource }
        }
    }

    fun getCharactersByAnimeID(animeID: Int) = viewModelScope.launch {
        Timber.i("Fetching characters for Anime ID $animeID")
        useCases.updateAnimeCharactersFetchedBefore(animeID = animeID)
        useCases.getCharacterByAnimeID(animeID = animeID).collectLatest { resource ->
            _characters.update { resource }
        }
    }

    fun updateAnimeByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.updateAnimeFavoriteByAnimeID(animeID = animeID)
    }
}
