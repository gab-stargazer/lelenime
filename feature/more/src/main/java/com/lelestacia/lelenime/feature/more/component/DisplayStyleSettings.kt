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
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.more.R

@Composable
fun DisplayStyleSettings(
    isOpened: Boolean,
    selectedStyle: DisplayStyle,
    onEvent: (DisplayStyle) -> Unit,
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
                text = stringResource(R.string.display_style_preferences),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = when (selectedStyle) {
                    DisplayStyle.CARD -> stringResource(id = com.lelestacia.lelenime.core.common.R.string.card)
                    DisplayStyle.COMPACT_CARD -> stringResource(id = com.lelestacia.lelenime.core.common.R.string.compact_card)
                    DisplayStyle.LIST -> stringResource(id = com.lelestacia.lelenime.core.common.R.string.list)
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
                contentDescription = null
            )
            DropdownMenu(
                expanded = isOpened,
                onDismissRequest = { onStateChanged.invoke() }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.card))
                    },
                    onClick = {
                        onEvent(DisplayStyle.CARD)
                        onStateChanged.invoke()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.compact_card)) },
                    onClick = {
                        onEvent(DisplayStyle.COMPACT_CARD)
                        onStateChanged.invoke()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.list)) },
                    onClick = {
                        onEvent(DisplayStyle.LIST)
                        onStateChanged.invoke()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDisplayStyleSettings() {
    LelenimeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        Surface {
            DisplayStyleSettings(
                isOpened = false,
                selectedStyle = DisplayStyle.CARD,
                onEvent = {},
                onStateChanged = {})
        }
    }
}

@Preview
@Composable
fun PreviewDisplayStyleSettingsDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Surface {
            DisplayStyleSettings(
                isOpened = false,
                selectedStyle = DisplayStyle.CARD,
                onEvent = {},
                onStateChanged = {})
        }
    }
}
