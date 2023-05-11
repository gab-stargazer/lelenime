package com.lelestacia.lelenime.core.database.userPreferences

import kotlinx.coroutines.flow.Flow

interface IUserPreferencesService {
    suspend fun getUserDisplayStyle(): Flow<Int>
    suspend fun updateUserDisplayStyle(displayStylePreferences: Int)
    suspend fun getUserTheme(): Flow<Int>
    suspend fun updateUserTheme(themePreferences: Int)
    suspend fun getUserPreferenceOnDynamicTheme(): Flow<Boolean>
    suspend fun updateUserPreferenceOnDynamicTheme(dynamicPreferences: Boolean)
    suspend fun isAnimeCharactersFetchedBefore(animeID: Int): Flow<Boolean>
    suspend fun updateAnimeCharactersFetchedBefore(animeID: Int)
}
