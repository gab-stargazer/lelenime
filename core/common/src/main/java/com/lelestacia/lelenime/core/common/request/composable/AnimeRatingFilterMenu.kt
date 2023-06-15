package com.lelestacia.lelenime.core.common.request.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.request.param.AnimeRating
import com.lelestacia.lelenime.core.common.request.param.ListOfAnimeRatings
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimeRatingFilterMenu(
    selectedAnimeRating: AnimeRating?,
    onAnimeRatingSelected: (AnimeRating?) -> Unit,
) {
    val listOfAnimeRatings: List<AnimeRating?> = ListOfAnimeRatings
    val allAnimeRating = stringResource(id = R.string.all_anime_rating)
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = R.string.anime_rating),
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
            listOfAnimeRatings.forEach { animeRating ->
                val textToDisplay = animeRating?.title
                    ?: allAnimeRating
                val isSelected = animeRating == selectedAnimeRating
                LelenimeFilterChip(
                    isActive = isSelected,
                    label = {
                        Text(text = textToDisplay)
                    },
                    leadingIcon = {},
                    trailingIcon = {},
                    onClicked = {
                        onAnimeRatingSelected(animeRating)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeRatingFilterMenu() {
    LelenimeTheme(
        dynamicColor = false
    ) {
        var currentRating: AnimeRating? by remember {
            mutableStateOf(null)
        }
        Surface {
            AnimeRatingFilterMenu(
                selectedAnimeRating = currentRating,
                onAnimeRatingSelected = { selectedRating ->
                    currentRating = selectedRating
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimeRatingFilterMenuDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        var currentRating: AnimeRating? by remember {
            mutableStateOf(null)
        }
        Surface {
            AnimeRatingFilterMenu(
                selectedAnimeRating = currentRating,
                onAnimeRatingSelected = { selectedRating ->
                    currentRating = selectedRating
                }
            )
        }
    }
}