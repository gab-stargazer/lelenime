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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.feature.more.R

@Composable
fun DarkModeSettings(
    isOpened: Boolean,
    selectedTheme: Int,
    onEvent: (Int) -> Unit,
    changeState: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
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
        IconButton(onClick = { changeState.invoke() }) {
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
                onDismissRequest = { changeState.invoke() }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.auto_theme_preferences))
                    },
                    onClick = {
                        onEvent(3)
                        changeState.invoke()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.day_theme_preferences)) },
                    onClick = {
                        onEvent(1)
                        changeState.invoke()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.dark_theme_preferences)) },
                    onClick = {
                        onEvent(2)
                        changeState.invoke()
                    }
                )
            }
        }
    }
}
