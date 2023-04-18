package com.lelestacia.lelenime.feature.more.screen.settings

import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle

sealed class SettingScreenEvent {
    data class UpdateDisplayStylePreferences(val displayStyle: DisplayStyle) : SettingScreenEvent()
    object DisplayStylePreferencesMenuStateChanged : SettingScreenEvent()
    data class UpdateDarkModePreferences(val darkModePreferences: Int) : SettingScreenEvent()
    object DarkModePreferencesMenuStateChanged : SettingScreenEvent()
    data class UpdateDynamicThemePreferences(val dynamicThemePreferences: Boolean) :
        SettingScreenEvent()

    object IsUpdateDynamicThemePreferencesStateChanged : SettingScreenEvent()
}
