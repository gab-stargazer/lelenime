package com.lelestacia.lelenime.feature.detail.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.domain.usecases.detail.IDetailUseCases
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: IDetailUseCases
) : ViewModel() {

    private val _anime: MutableStateFlow<Resource<Anime>> = MutableStateFlow(Resource.None)
    val anime: StateFlow<Resource<Anime>> get() = _anime.asStateFlow()

    private val _characters: MutableStateFlow<Resource<List<Character>>> =
        MutableStateFlow(Resource.None)
    val characters: StateFlow<Resource<List<Character>>> get() = _characters.asStateFlow()

    fun initiateView(animeID: Int) {
        if (_anime.value is Resource.None) {
            getAnimeByAnimeID(animeID)
        }

        if (_characters.value is Resource.None || _characters.value is Resource.Error) {
            getCharactersByAnimeID(animeID)
        }
    }

    private fun getAnimeByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.getAnimeFromLocalDatabaseByAnimeID(animeID = animeID).collect { resource ->
            _anime.update { resource }
        }
    }

    private fun getCharactersByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.getCharacterByAnimeID(animeID = animeID).collect { resource ->
            _characters.update { resource }
        }
    }

    fun updateAnimeByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.updateAnimeFavoriteByAnimeID(animeID = animeID)
    }
}
