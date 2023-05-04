package com.lelestacia.lelenime.feature.detail.screen.detailCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.domain.usecases.detailCharacter.IDetailCharacterUseCases
import com.lelestacia.lelenime.core.model.character.CharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val useCases: IDetailCharacterUseCases
) : ViewModel() {

    private val _characterResource: MutableStateFlow<Resource<CharacterDetail>> =
        MutableStateFlow(Resource.None)
    val characterResource = _characterResource.asStateFlow()

    fun fetchDetailCharacter(characterID: Int) = viewModelScope.launch {
        useCases.getCharacterInformationByCharacterId(characterID).collectLatest { resource ->
            Timber.d("Current Resource : $resource")
            _characterResource.update { resource }
        }
    }
}
