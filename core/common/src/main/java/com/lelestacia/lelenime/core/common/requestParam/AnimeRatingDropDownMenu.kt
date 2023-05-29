package com.lelestacia.lelenime.core.common.requestParam

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun AnimeRatingDropDownMenu(
    selectedAnimeRating: AnimeRating?,
    isAnimeRatingMenuOpened: Boolean,
    onDismiss: (Boolean) -> Unit,
    onAnimeRatingSelected: (AnimeRating?) -> Unit
) {
    DropdownMenu(
        expanded = isAnimeRatingMenuOpened,
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
                Text(text = "All Rating")
            },
            onClick = {
                onAnimeRatingSelected(null)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeRating == null,
                    onClick = {
                        onAnimeRatingSelected(null)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = AnimeRating.G.title)
            },
            onClick = {
                onAnimeRatingSelected(AnimeRating.G)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeRating == AnimeRating.G,
                    onClick = {
                        onAnimeRatingSelected(AnimeRating.G)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = AnimeRating.PG.title)
            },
            onClick = {
                onAnimeRatingSelected(AnimeRating.PG)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeRating == AnimeRating.PG,
                    onClick = {
                        onAnimeRatingSelected(AnimeRating.PG)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = AnimeRating.PG_13.title)
            },
            onClick = {
                onAnimeRatingSelected(AnimeRating.PG_13)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeRating == AnimeRating.PG_13,
                    onClick = {
                        onAnimeRatingSelected(AnimeRating.PG_13)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = AnimeRating.R.title)
            },
            onClick = {
                onAnimeRatingSelected(AnimeRating.R)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeRating == AnimeRating.R,
                    onClick = {
                        onAnimeRatingSelected(AnimeRating.R)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = AnimeRating.R_17.title)
            },
            onClick = {
                onAnimeRatingSelected(AnimeRating.R_17)
            },
            trailingIcon = {
                RadioButton(
                    selected = selectedAnimeRating == AnimeRating.R_17,
                    onClick = {
                        onAnimeRatingSelected(AnimeRating.R_17)
                    }
                )
            }
        )
    }
}
