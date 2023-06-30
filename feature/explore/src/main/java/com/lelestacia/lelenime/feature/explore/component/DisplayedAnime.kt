package com.lelestacia.lelenime.feature.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayTypeButton
import com.lelestacia.lelenime.feature.explore.component.displayType.ExplorationDisplayType
import com.lelestacia.lelenime.feature.explore.stateAndEvent.BottomSheetEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState

@Composable
fun DisplayedAnimeMenu(
    state: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier
    ) {
        items(items = ExplorationDisplayType) { displayType ->
            DisplayTypeButton(
                isActive = state.displayType == displayType,
                displayType = displayType,
                icon = when (displayType) {
                    DisplayType.POPULAR -> Icons.Default.Favorite
                    DisplayType.AIRING -> Icons.Default.MovieFilter
                    DisplayType.UPCOMING -> Icons.Default.Upcoming
                    DisplayType.SEARCH -> Icons.Default.Search
                },
                onClicked = {
                    onEvent(BottomSheetEvent.OnDisplayTypeChanged(it))
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopHeader() {
    DisplayedAnimeMenu(
        state = ExploreScreenState(),
        onEvent = {}
    )
}
