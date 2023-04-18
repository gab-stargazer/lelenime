package com.lelestacia.lelenime.core.domain.usecases.settings

import com.lelestacia.lelenime.core.data.repository.IUserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesUseCases @Inject constructor(
    private val userPreferencesRepository: IUserPreferencesRepository
) : IUserPreferencesUseCases {
    override suspend fun getUserDisplayStyle(): Flow<Int> =
        userPreferencesRepository.getUserDisplayStyle()

    override suspend fun updateUserDisplayStyle(displayStylePreferences: Int) {
        userPreferencesRepository.updateUserDisplayStyle(
            displayStylePreferences = displayStylePreferences
        )
    }

    override suspend fun getUserTheme(): Flow<Int> =
        userPreferencesRepository.getUserTheme()

    override suspend fun updateUserTheme(themePreferences: Int) {
        userPreferencesRepository.updateUserTheme(
            themePreferences = themePreferences
        )
    }

    override suspend fun getUserPreferenceOnDynamicTheme(): Flow<Boolean> =
        userPreferencesRepository.getUserPreferenceOnDynamicTheme()

    override suspend fun updateUserPreferenceOnDynamicTheme(dynamicPreferences: Boolean) {
        userPreferencesRepository.updateUserPreferenceOnDynamicTheme(
            dynamicPreferences = dynamicPreferences
        )
    }
}
