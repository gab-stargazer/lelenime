package com.lelestacia.lelenime.feature.explore.component.filter

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
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.requestParam.AnimeType
import com.lelestacia.lelenime.core.common.requestParam.ListOfAnimeType
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.stateAndEvent.PopularAnimeFilter
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimeTypeFilterMenu(
    selectedAnimeType: AnimeType?,
    onAnimeTypeChanged: (AnimeType?) -> Unit
) {
    val listOfAnimeType = ListOfAnimeType
    val allAnimeType: String = stringResource(id = R.string.all_anime_type)

    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = R.string.anime_type),
            style = MaterialTheme.typography.titleMedium
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOfAnimeType.forEach { animeType ->
                val textToDisplay = animeType?.name?.lowercase()
                    ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                    ?: allAnimeType
                val isSelected = animeType == selectedAnimeType
                LelenimeFilterChip(
                    isActive = isSelected,
                    label = {
                        Text(text = textToDisplay)
                    },
                    leadingIcon = {},
                    trailingIcon = {},
                    onClicked = {
                        onAnimeTypeChanged(animeType)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeTypeFilter() {
    LelenimeTheme(dynamicColor = false) {
        var popularAnimeFilter by remember {
            mutableStateOf(PopularAnimeFilter())
        }
        Surface {
            AnimeTypeFilterMenu(
                selectedAnimeType = popularAnimeFilter.animeType,
                onAnimeTypeChanged = { selectedAnimeType ->
                    popularAnimeFilter = popularAnimeFilter.copy(animeType = selectedAnimeType)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimeTypeFilterDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        var popularAnimeFilter by remember {
            mutableStateOf(PopularAnimeFilter())
        }
        Surface {
            AnimeTypeFilterMenu(
                selectedAnimeType = popularAnimeFilter.animeType,
                onAnimeTypeChanged = { selectedAnimeType ->
                    popularAnimeFilter = popularAnimeFilter.copy(animeType = selectedAnimeType)
                }
            )
        }
    }
}
