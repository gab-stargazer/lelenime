package com.lelestacia.lelenime.core.common.requestParam

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun AnimeStatusDropDownMenu(
    selectedAnimeStatus: AnimeStatus?,
    isAnimeStatusMenuOpened: Boolean,
    onDismiss: (Boolean) -> Unit,
    onAnimeStatusSelected: (AnimeStatus?) -> Unit
) {
    DropdownMenu(
        expanded = isAnimeStatusMenuOpened,
        onDismissRequest = {
            onDismiss(false)
        },
        offset = DpOffset(
            x = 125.dp,
            y = 0.dp
        )
    ) {
        DropdownMenuItem(
            text = {
                Text(text = "All Status")
            },
            onClick = {
                onAnimeStatusSelected(null)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeStatus == null,
                    onClick = {
                        onAnimeStatusSelected(null)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeStatus.AIRING.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeStatusSelected(AnimeStatus.AIRING)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeStatus == AnimeStatus.AIRING,
                    onClick = {
                        onAnimeStatusSelected(AnimeStatus.AIRING)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeStatus.COMPLETE.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeStatusSelected(AnimeStatus.COMPLETE)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeStatus == AnimeStatus.COMPLETE,
                    onClick = {
                        onAnimeStatusSelected(AnimeStatus.COMPLETE)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeStatus.UPCOMING.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeStatusSelected(AnimeStatus.UPCOMING)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeStatus == AnimeStatus.UPCOMING,
                    onClick = {
                        onAnimeStatusSelected(AnimeStatus.UPCOMING)
                    }
                )
            }
        )
    }
}
