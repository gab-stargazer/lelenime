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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.request.param.ListOfPopularAnimeFilter
import com.lelestacia.lelenime.core.common.request.param.PopularAnimeFilter
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularAnimeFilter(
    selectedPopularAnimeFilter: PopularAnimeFilter?,
    onPopularAnimeFilterChanged: (PopularAnimeFilter?) -> Unit
) {
    val listOfPopularAnimeFilter = ListOfPopularAnimeFilter
    val noAnimeFilter: String = stringResource(id = R.string.no_popular_anime_filter)

    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = R.string.popular_anime_filter),
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
            listOfPopularAnimeFilter.forEach { popularAnimeFilter ->
                val textToDisplay = popularAnimeFilter?.title ?: noAnimeFilter
                val isSelected = popularAnimeFilter == selectedPopularAnimeFilter
                LelenimeFilterChip(
                    isActive = isSelected,
                    label = {
                        Text(text = textToDisplay)
                    },
                    leadingIcon = {},
                    trailingIcon = {},
                    onClicked = {
                        onPopularAnimeFilterChanged(popularAnimeFilter)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPopularAnimeFilter() {
    LelenimeTheme(dynamicColor = false) {
        Surface {
            PopularAnimeFilter(
                selectedPopularAnimeFilter = null,
                onPopularAnimeFilterChanged = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewPopularAnimeFilterDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Surface {
            PopularAnimeFilter(
                selectedPopularAnimeFilter = null,
                onPopularAnimeFilterChanged = {}
            )
        }
    }
}