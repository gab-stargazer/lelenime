package com.lelestacia.lelenime.core.data.repository

import kotlinx.coroutines.flow.Flow

interface IUserPreferencesRepository {

    /**
     * Gets the user's preferred display style from the local database.
     *
     * @return a flow of integer representing the display style preference of the user.
     */
    suspend fun getUserDisplayStyle(): Flow<Int>

    /**
     * Updates the user's preferred display style in the local database.
     *
     * @param displayStylePreferences an integer representing the new display style preference of the user.
     */
    suspend fun updateUserDisplayStyle(displayStylePreferences: Int)

    /**
     * Gets the user's preferred theme from the local database.
     *
     * @return a flow of integer representing the theme preference of the user.
     */
    suspend fun getUserTheme(): Flow<Int>

    /**
     * Updates the user's preferred theme in the local database.
     *
     * @param themePreferences an integer representing the new theme preference of the user.
     */
    suspend fun updateUserTheme(themePreferences: Int)

    /**
     * Gets the user's preference on dynamic theme changes from the local database.
     *
     * @return a flow of boolean representing the dynamic theme preference of the user.
     */
    suspend fun getUserPreferenceOnDynamicTheme(): Flow<Boolean>

    /**
     * Updates the user's preference on dynamic theme changes in the local database.
     *
     * @param dynamicPreferences a boolean representing the new dynamic theme preference of the user.
     */
    suspend fun updateUserPreferenceOnDynamicTheme(dynamicPreferences: Boolean)
    suspend fun isAnimeCharactersFetchedBefore(animeID: Int): Flow<Boolean>
    suspend fun updateAnimeCharactersFetchedBefore(animeID: Int)
}
