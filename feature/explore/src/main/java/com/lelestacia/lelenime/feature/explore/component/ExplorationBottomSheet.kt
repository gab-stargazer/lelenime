package com.lelestacia.lelenime.feature.explore.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.feature.explore.component.filter.AnimeDisplayTypeMenu
import com.lelestacia.lelenime.feature.explore.component.filter.AnimeTypeFilterMenu
import com.lelestacia.lelenime.feature.explore.screen.DisplayType
import com.lelestacia.lelenime.feature.explore.stateAndEvent.AnimeFilter
import com.lelestacia.lelenime.feature.explore.stateAndEvent.BottomSheetEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreBottomSheetState
import com.lelestacia.lelenime.feature.explore.stateAndEvent.OnAnimeFilterChanged
import com.lelestacia.lelenime.feature.explore.stateAndEvent.OnDisplayTypeChanged

@Composable
fun ExplorationBottomSheet(
    state: ExploreBottomSheetState,
    onEvent: (BottomSheetEvent) -> Unit
) {
    Surface {
        Column {
            AnimeDisplayTypeMenu(
                selectedDisplayType = state.displayType,
                onEvent = { selectedDisplayType ->
                    onEvent(OnDisplayTypeChanged(selectedDisplayType))
                }
            )

            AnimeTypeFilterMenu(
                selectedAnimeType = when (state.displayType) {
                    DisplayType.POPULAR -> state.animeFilter.popularAnimeFilter.animeType
                    DisplayType.AIRING -> state.animeFilter.airingAnimeFilter.animeType
                    DisplayType.UPCOMING -> state.animeFilter.upcomingAnimeFilter.animeType
                    DisplayType.SEARCH -> state.animeFilter.searchAnimeFilter.animeType
                },
                onAnimeTypeChanged = { selectedAnimeType ->
                    val modifiedAnimeType: AnimeFilter =
                        when (state.displayType) {
                            DisplayType.POPULAR -> state.animeFilter.copy(
                                popularAnimeFilter = state.animeFilter.popularAnimeFilter.copy(
                                    animeType = selectedAnimeType
                                )
                            )

                            DisplayType.AIRING -> state.animeFilter.copy(
                                airingAnimeFilter = state.animeFilter.airingAnimeFilter.copy(
                                    animeType = selectedAnimeType
                                )
                            )

                            DisplayType.UPCOMING -> state.animeFilter.copy(
                                upcomingAnimeFilter = state.animeFilter.upcomingAnimeFilter.copy(
                                    animeType = selectedAnimeType
                                )
                            )

                            DisplayType.SEARCH -> state.animeFilter.copy(
                                searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                    animeType = selectedAnimeType
                                )
                            )
                        }
                    onEvent(
                        OnAnimeFilterChanged(modifiedAnimeType)
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExplorationBottomSheet() {
    LelenimeTheme(
        dynamicColor = false
    ) {
        var state by remember {
            mutableStateOf(ExploreBottomSheetState())
        }
        Column {
            Text(text = state.toString())
            ExplorationBottomSheet(
                state = state,
                onEvent = {
                    state = when (it) {
                        is OnDisplayTypeChanged -> {
                            state.copy(
                                displayType = it.displayType
                            )
                        }

                        is OnAnimeFilterChanged -> {
                            state.copy(
                                animeFilter = it.animeFilter
                            )
                        }
                    }
                }
            )
        }
    }
}
