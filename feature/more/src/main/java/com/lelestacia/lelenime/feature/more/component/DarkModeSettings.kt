package com.lelestacia.lelenime.feature.more.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.more.R

@Composable
fun DarkModeSettings(
    isOpened: Boolean,
    selectedTheme: Int,
    onEvent: (Int) -> Unit,
    onStateChanged: () -> Unit
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
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.spacing.extraSmall
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.dark_mode_preferences),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = when (selectedTheme) {
                    1 -> stringResource(id = R.string.day_theme_preferences)
                    2 -> stringResource(id = R.string.dark_theme_preferences)
                    else -> stringResource(id = R.string.auto_theme_preferences)
                },
                style = MaterialTheme.typography.titleMedium
            )
        }
        IconButton(onClick = { onStateChanged.invoke() }) {
            Icon(
                imageVector =
                if (isOpened) {
                    Icons.Filled.ArrowDropUp
                } else {
                    Icons.Filled.ArrowDropDown
                },
                contentDescription = stringResource(id = R.string.dark_mode_preferences)
            )
            DropdownMenu(
                expanded = isOpened,
                onDismissRequest = { onStateChanged.invoke() }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.auto_theme_preferences))
                    },
                    onClick = {
                        onEvent(3)
                        onStateChanged.invoke()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.day_theme_preferences)) },
                    onClick = {
                        onEvent(1)
                        onStateChanged.invoke()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.dark_theme_preferences)) },
                    onClick = {
                        onEvent(2)
                        onStateChanged.invoke()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDarkModeSettings() {
    LelenimeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        Surface {
            DarkModeSettings(
                isOpened = false,
                selectedTheme = 1,
                onEvent = {},
                onStateChanged = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDarkModeSettingsDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Surface {
            DarkModeSettings(
                isOpened = false,
                selectedTheme = 1,
                onEvent = {},
                onStateChanged = {}
            )
        }
    }
}
