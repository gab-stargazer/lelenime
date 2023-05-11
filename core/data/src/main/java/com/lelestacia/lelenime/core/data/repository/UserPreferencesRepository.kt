package com.lelestacia.lelenime.core.data.repository

import com.lelestacia.lelenime.core.database.userPreferences.IUserPreferencesService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferencesService: IUserPreferencesService
) : IUserPreferencesRepository {
    override suspend fun getUserDisplayStyle(): Flow<Int> =
        userPreferencesService.getUserDisplayStyle()

    override suspend fun updateUserDisplayStyle(displayStylePreferences: Int) {
        userPreferencesService.updateUserDisplayStyle(
            displayStylePreferences = displayStylePreferences
        )
    }

    override suspend fun getUserTheme(): Flow<Int> =
        userPreferencesService.getUserTheme()

    override suspend fun updateUserTheme(themePreferences: Int) {
        userPreferencesService.updateUserTheme(
            themePreferences = themePreferences
        )
    }

    override suspend fun getUserPreferenceOnDynamicTheme(): Flow<Boolean> =
        userPreferencesService.getUserPreferenceOnDynamicTheme()

    override suspend fun updateUserPreferenceOnDynamicTheme(dynamicPreferences: Boolean) {
        userPreferencesService.updateUserPreferenceOnDynamicTheme(
            dynamicPreferences = dynamicPreferences
        )
    }

    override suspend fun isAnimeCharactersFetchedBefore(animeID: Int): Flow<Boolean> =
        userPreferencesService.isAnimeCharactersFetchedBefore(animeID = animeID)

    override suspend fun updateAnimeCharactersFetchedBefore(animeID: Int) {
        userPreferencesService.updateAnimeCharactersFetchedBefore(animeID = animeID)
    }
}
