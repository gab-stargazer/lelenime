package com.lelestacia.lelenime.feature.more.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.more.R
import com.lelestacia.lelenime.feature.more.screen.settings.SettingScreenEvent
import com.lelestacia.lelenime.feature.more.screen.settings.SettingScreenState

@Composable
fun DynamicColorSettings(
    state: SettingScreenState,
    onEvent: (SettingScreenEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.large,
                vertical = MaterialTheme.spacing.default
            )
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.spacing.extraSmall
            )
        ) {
            Text(
                text = stringResource(R.string.dynamic_color),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text =
                if (state.dynamicThemePreferences) {
                    stringResource(R.string.using_dynamic_color)
                } else {
                    stringResource(R.string.using_static_color)
                },
                style = MaterialTheme.typography.titleMedium
            )
        }
        Switch(
            checked = state.dynamicThemePreferences,
            onCheckedChange = { newPreferences ->
                onEvent(SettingScreenEvent.UpdateDynamicThemePreferences(newPreferences))
            }
        )
    }
}

@Preview
@Composable
fun PreviewDynamicColorSettings() {
    LelenimeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        Surface {
            DynamicColorSettings(state = SettingScreenState(), onEvent = {})
        }
    }
}

@Preview
@Composable
fun PreviewDynamicColorSettingsDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Surface {
            DynamicColorSettings(state = SettingScreenState(), onEvent = {})
        }
    }
}
