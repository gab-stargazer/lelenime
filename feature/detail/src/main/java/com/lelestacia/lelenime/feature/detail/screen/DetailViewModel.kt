package com.lelestacia.lelenime.feature.detail.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.domain.usecases.detail.IDetailUseCases
import com.lelestacia.lelenime.core.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: IDetailUseCases
) : ViewModel() {

    private val _anime: MutableStateFlow<Resource<Anime>> = MutableStateFlow(Resource.None)
    val anime: StateFlow<Resource<Anime>> get() = _anime.asStateFlow()

    fun getAnimeByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.getAnimeFromLocalDatabaseByAnimeID(animeID = animeID).collect { resource ->
            _anime.value = resource
        }
    }

    fun updateAnimeByAnimeID(animeID: Int) = viewModelScope.launch {
        useCases.updateAnimeFavoriteByAnimeID(animeID = animeID)
    }
}
