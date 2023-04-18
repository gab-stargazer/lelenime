package com.lelestacia.lelenime.feature.explore.component.header

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyleMenu
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardSearchHeader(
    screenState: ExploreScreenState,
    onEvent: (ExploreScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.animateContentSize()
    ) {
        if (screenState.headerScreenState.isSearching) {
            IconButton(onClick = { onEvent(ExploreScreenEvent.OnStopSearching) }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close search mode")
            }
            TextField(
                value = screenState.headerScreenState.searchQuery,
                onValueChange = { newSearchQuery ->
                    onEvent(ExploreScreenEvent.OnSearchQueryChanged(newSearchQuery))
                },
                shape = RoundedCornerShape(4.dp),
                label = {
                    Text(text = "Insert Anime Title")
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions {
                    onEvent(ExploreScreenEvent.OnSearch)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    onEvent(ExploreScreenEvent.OnStartSearching)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Anime"
                )
            }
        }
        IconButton(
            onClick = {
                onEvent(ExploreScreenEvent.OnDisplayStyleOptionMenuStateChanged)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.GridView,
                contentDescription = "Display Style"
            )
            DisplayStyleMenu(
                currentStyle = screenState.displayStyle,
                isExpanded = screenState.headerScreenState.isDisplayStyleOptionOpened,
                onStyleChanged = {
                    onEvent(ExploreScreenEvent.OnDisplayStyleChanged(it))
                },
                onDismiss = {
                    onEvent(ExploreScreenEvent.OnDisplayStyleOptionMenuStateChanged)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewDashboardTopHeader() {
    Surface {
        DashboardSearchHeader(
            screenState = ExploreScreenState(),
            onEvent = {}
        )
    }
}

@Preview
@Composable
fun PreviewDashboardTopHeaderSearchModeEnabled() {
    Surface {
        DashboardSearchHeader(
            screenState = ExploreScreenState(
                headerScreenState = HeaderScreenState(
                    searchQuery = "Bocchi The Rock",
                    isSearching = true
                )
            ),
            onEvent = {}
        )
    }
}
