package com.lelestacia.lelenime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.lelenime.core.domain.usecases.settings.IUserPreferencesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val useCases: IUserPreferencesUseCases
) : ViewModel() {

    val darkModePreferences: MutableStateFlow<Int> = MutableStateFlow(3)
    val dynamicMode: MutableStateFlow<Boolean> = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            useCases.getUserTheme().collectLatest { theme ->
                darkModePreferences.update { theme }
            }
        }

        viewModelScope.launch {
            useCases.getUserPreferenceOnDynamicTheme().collectLatest { dynamicPrefences ->
                dynamicMode.update { dynamicPrefences }
            }
        }
    }
}
