package com.lelestacia.lelenime.core.common.request.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.lelestacia.lelenime.core.common.request.param.AnimeSort
import com.lelestacia.lelenime.core.common.request.param.ListOfAnimeSort
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing

@Composable
fun AnimeSortFilterMenu(
    selectedAnimeSort: AnimeSort,
    onAnimeSortChanged: (AnimeSort) -> Unit,
) {
    val listOfAnimeSort: List<AnimeSort> = ListOfAnimeSort
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = R.string.sort_anime_by),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOfAnimeSort.forEach { sort: AnimeSort ->
                LelenimeFilterChip(
                    isActive = sort == selectedAnimeSort,
                    label = {
                        Text(text = sort.title)
                    },
                    leadingIcon = {},
                    trailingIcon = {},
                    onClicked = {
                        onAnimeSortChanged(sort)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeSortFilterMenu() {
    LelenimeTheme(dynamicColor = false) {
        var animeSort: AnimeSort by remember {
            mutableStateOf(AnimeSort.ASC)
        }
        Surface {
            AnimeSortFilterMenu(
                selectedAnimeSort = animeSort,
                onAnimeSortChanged = { selectedAnimeSort ->
                    animeSort = selectedAnimeSort
                })
        }
    }
}

@Preview
@Composable
fun PreviewAnimeSortFilterMenuDarkmode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        var animeSort: AnimeSort by remember {
            mutableStateOf(AnimeSort.ASC)
        }
        Surface {
            AnimeSortFilterMenu(
                selectedAnimeSort = animeSort,
                onAnimeSortChanged = { selectedAnimeSort ->
                    animeSort = selectedAnimeSort
                })
        }
    }
}