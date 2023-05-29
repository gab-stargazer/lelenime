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
fun AnimeTypeDropDownMenu(
    selectedAnimeType: AnimeType?,
    isAnimeTypeMenuOpened: Boolean,
    onDismiss: (Boolean) -> Unit,
    onAnimeTypeSelected: (AnimeType?) -> Unit
) {
    DropdownMenu(
        expanded = isAnimeTypeMenuOpened,
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
                Text(text = "All Type")
            },
            onClick = {
                onAnimeTypeSelected(null)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeType == null,
                    onClick = {
                        onAnimeTypeSelected(null)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeType.TV.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeTypeSelected(AnimeType.TV)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeType == AnimeType.TV,
                    onClick = {
                        onAnimeTypeSelected(AnimeType.TV)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeType.MOVIE.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeTypeSelected(AnimeType.MOVIE)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeType == AnimeType.MOVIE,
                    onClick = {
                        onAnimeTypeSelected(AnimeType.MOVIE)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeType.OVA.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeTypeSelected(AnimeType.OVA)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeType == AnimeType.OVA,
                    onClick = {
                        onAnimeTypeSelected(AnimeType.OVA)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeType.SPECIAL.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeTypeSelected(AnimeType.SPECIAL)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeType == AnimeType.SPECIAL,
                    onClick = {
                        onAnimeTypeSelected(AnimeType.SPECIAL)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeType.ONA.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeTypeSelected(AnimeType.ONA)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeType == AnimeType.ONA,
                    onClick = {
                        onAnimeTypeSelected(AnimeType.ONA)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(
                    text = AnimeType.MUSIC.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                )
            },
            onClick = {
                onAnimeTypeSelected(AnimeType.MUSIC)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeType == AnimeType.MUSIC,
                    onClick = {
                        onAnimeTypeSelected(AnimeType.MUSIC)
                    }
                )
            }
        )
    }
}
