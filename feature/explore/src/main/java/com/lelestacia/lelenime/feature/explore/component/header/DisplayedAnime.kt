package com.lelestacia.lelenime.feature.explore.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayTypeButton
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState

/**
 * Composable function that displays the header for selecting the dashboard display type.
 *
 * @param state The current state of the Explore screen, containing the selected [DisplayType].
 * @param onEvent The callback function that will be invoked when a display type is selected.
 *               It takes an [ExploreScreenEvent] as the parameter, indicating the selected display type.
 * @param modifier Optional [Modifier] that can be used to modify the layout behavior or appearance of the header.
 */
@Composable
fun DashboardDisplayTypeHeader(
    state: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            DisplayTypeButton(
                isActive = state.displayType == DisplayType.POPULAR,
                displayType = DisplayType.POPULAR,
                icon = Icons.Filled.Favorite,
                onClicked = {
                    onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.POPULAR))
                }
            )
            DisplayTypeButton(
                isActive = state.displayType == DisplayType.AIRING,
                displayType = DisplayType.AIRING,
                icon = Icons.Filled.MovieFilter,
                onClicked = {
                    onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.AIRING))
                }
            )
            DisplayTypeButton(
                isActive = state.displayType == DisplayType.UPCOMING,
                displayType = DisplayType.UPCOMING,
                icon = Icons.Filled.Upcoming,
                onClicked = {
                    onEvent(ExploreScreenEvent.OnDisplayTypeChanged(DisplayType.UPCOMING))
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopHeader() {
    LelenimeTheme(
        dynamicColor = false
    ) {
        DashboardDisplayTypeHeader(
            state = ExploreScreenState(),
            onEvent = {}
        )
    }
}
