package com.lelestacia.lelenime.core.common.request.composable

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.request.param.AnimeGenre
import com.lelestacia.lelenime.core.common.request.param.ListOfAnimeGenres
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimeGenreFilterMenu(
    selectedAnimeGenres: List<AnimeGenre?>,
    onAnimeGenreAdded: (AnimeGenre) -> Unit,
    onAnimeGenreRemoved: (AnimeGenre) -> Unit,
    onAnimeGenreCleared: ()-> Unit
) {
    val listOfAnimeGenre = ListOfAnimeGenres
    val allGenres = stringResource(id = R.string.all_anime_genre)
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = R.string.anime_genre),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOfAnimeGenre.forEach { genre: AnimeGenre? ->
                val textToDisplay = genre?.title ?: allGenres
                val isGenreExistOnList = selectedAnimeGenres.contains(genre)
                LelenimeFilterChip(
                    isActive =
                    if (genre == null) {
                        selectedAnimeGenres.isEmpty()
                    } else {
                        selectedAnimeGenres.contains(genre)
                    },
                    label = {
                        Text(text = textToDisplay)
                    },
                    leadingIcon = {},
                    trailingIcon = {
                        genre?.let {
                            Crossfade(
                                targetState = isGenreExistOnList,
                                label = "icon"
                            ) { isExist ->
                                if (isExist) {
                                    Icon(
                                        imageVector = Icons.Default.Remove,
                                        contentDescription = null,
                                        modifier = Modifier.size(12.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }
                        }
                    },
                    onClicked = {
                        genre?.let {
                            if (isGenreExistOnList) onAnimeGenreRemoved(it)
                            else onAnimeGenreAdded(it)
                            return@LelenimeFilterChip
                        }
                        onAnimeGenreCleared()
                    })
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeGenresFilterMenu() {
    LelenimeTheme(
        dynamicColor = false
    ) {
        val genres = remember {
            mutableStateListOf<AnimeGenre?>()
        }
        Surface {
            AnimeGenreFilterMenu(
                selectedAnimeGenres = genres,
                onAnimeGenreAdded = { genre: AnimeGenre ->
                    genres.add(genre)
                },
                onAnimeGenreRemoved = { genre: AnimeGenre ->
                    genres.remove(genre)
                },
                onAnimeGenreCleared = {
                    genres.clear()
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimeGenresFilterMenuDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        val genres = remember {
            mutableStateListOf<AnimeGenre?>()
        }
        Surface {
            AnimeGenreFilterMenu(
                selectedAnimeGenres = genres,
                onAnimeGenreAdded = { genre: AnimeGenre ->
                    genres.add(genre)
                },
                onAnimeGenreRemoved = { genre: AnimeGenre ->
                    genres.remove(genre)
                },
                onAnimeGenreCleared = {
                    genres.clear()
                }
            )
        }
    }
}