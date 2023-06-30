package com.lelestacia.lelenime.feature.explore.component.displayType

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.R.string.displayed_anime
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import java.util.Locale

/**
 * Composable function that displays a menu for selecting the anime display type.
 *
 * @param selectedDisplayType The currently selected [DisplayType] in the menu.
 * @param onEvent The callback function that will be invoked when the display type is changed.
 *               It provides the newly selected [DisplayType] as the parameter.
 */
@Composable
@OptIn(ExperimentalLayoutApi::class)
fun AnimeDisplayTypeMenu(
    selectedDisplayType: DisplayType,
    onEvent: (DisplayType) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = displayed_anime),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        val allExplorationDisplayType: List<DisplayType> = ExplorationDisplayType
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.spacing.small
            ),
            maxItemsInEachRow = 2
        ) {
            allExplorationDisplayType.forEach { displayType ->
                val textToDisplay = displayType.name
                    .lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                LelenimeFilterChip(
                    isActive = selectedDisplayType == displayType,
                    onClicked = {
                        onEvent(displayType)
                    },
                    label = {
                        Text(
                            text = textToDisplay,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    },
                    trailingIcon = {},
                    leadingIcon = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeDisplayTypeMenu() {
    LelenimeTheme(
        dynamicColor = false
    ) {
        var state by remember {
            mutableStateOf(DisplayType.POPULAR)
        }
        Surface {
            AnimeDisplayTypeMenu(
                selectedDisplayType = state,
                onEvent = {
                    state = it
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimeDisplayTypeMenuDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        var state by remember {
            mutableStateOf(DisplayType.POPULAR)
        }
        Surface {
            AnimeDisplayTypeMenu(
                selectedDisplayType = state,
                onEvent = {
                    state = it
                }
            )
        }
    }
}
