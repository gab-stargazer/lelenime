package com.lelestacia.lelenime.feature.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.feature.explore.screen.DisplayType
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState

@Composable
fun DashboardDisplayTypeHeader(
    state: ExploreScreenState,
    isDarkMode: Boolean,
    onEvent: (ExploreScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.POPULAR,
            isDarkMode = isDarkMode,
            displayType = DisplayType.POPULAR,
            icon = Icons.Filled.Favorite,
            onClicked = {
                onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.POPULAR))
            }
        )
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.AIRING,
            isDarkMode = isDarkMode,
            displayType = DisplayType.AIRING,
            icon = Icons.Filled.MovieFilter,
            onClicked = {
                onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.AIRING))
            }
        )
        DisplayTypeButton(
            isActive = state.displayType == DisplayType.UPCOMING,
            isDarkMode = isDarkMode,
            displayType = DisplayType.UPCOMING,
            icon = Icons.Filled.Upcoming,
            onClicked = {
                onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.UPCOMING))
            }
        )
    }
}

@Preview
@Composable
fun PreviewTopHeader() {
    DashboardDisplayTypeHeader(
        state = ExploreScreenState(),
        isDarkMode = false,
        onEvent = {}
    )
}
