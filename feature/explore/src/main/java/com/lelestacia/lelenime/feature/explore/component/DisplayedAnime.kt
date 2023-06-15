package com.lelestacia.lelenime.feature.explore.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState

@Composable
fun DisplayedAnimeMenu(
    state: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(start = MaterialTheme.spacing.medium)
            .horizontalScroll(rememberScrollState())
    ) {
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.POPULAR,
            displayType = DisplayType.POPULAR,
            icon = Icons.Filled.Favorite
        ) {
            onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.POPULAR))
        }
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.AIRING,
            displayType = DisplayType.AIRING,
            icon = Icons.Filled.MovieFilter
        ) {
            onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.AIRING))
        }
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.UPCOMING,
            displayType = DisplayType.UPCOMING,
            icon = Icons.Filled.Upcoming
        ) {
            onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.UPCOMING))
        }
    }
}

@Preview
@Composable
fun PreviewTopHeader() {
    DisplayedAnimeMenu(
        state = ExploreScreenState()
    ) {}
}
