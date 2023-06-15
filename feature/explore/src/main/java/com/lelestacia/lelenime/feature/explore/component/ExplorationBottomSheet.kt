package com.lelestacia.lelenime.feature.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.request.composable.AnimeGenreFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeRatingFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeSortFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeStatusFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeTypeFilterMenu
import com.lelestacia.lelenime.core.common.request.param.AnimeGenre
import com.lelestacia.lelenime.core.common.request.param.AnimeRating
import com.lelestacia.lelenime.core.common.request.param.AnimeSort
import com.lelestacia.lelenime.core.common.request.param.AnimeStatus
import com.lelestacia.lelenime.core.common.request.param.AnimeType
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.component.filter.AnimeDisplayTypeMenu
import com.lelestacia.lelenime.feature.explore.stateAndEvent.AnimeFilter
import com.lelestacia.lelenime.feature.explore.stateAndEvent.BottomSheetEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreBottomSheetState
import com.lelestacia.lelenime.feature.explore.stateAndEvent.OnAnimeFilterChanged
import com.lelestacia.lelenime.feature.explore.stateAndEvent.OnDisplayTypeChanged

@Composable
fun ExplorationBottomSheet(
    state: ExploreBottomSheetState,
    onEvent: (BottomSheetEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.medium
            ),
            contentPadding = PaddingValues(
                vertical = MaterialTheme.spacing.medium
            ),
        ) {
            item {
                AnimeDisplayTypeMenu(
                    selectedDisplayType = state.displayType,
                    onEvent = { selectedDisplayType: DisplayType ->
                        onEvent(OnDisplayTypeChanged(selectedDisplayType))
                    }
                )
            }

            item {
                AnimeTypeFilterMenu(
                    selectedAnimeType = when (state.displayType) {
                        DisplayType.POPULAR -> state.animeFilter.popularAnimeFilter.type
                        DisplayType.AIRING -> state.animeFilter.airingAnimeFilter.type
                        DisplayType.UPCOMING -> state.animeFilter.upcomingAnimeFilter.type
                        DisplayType.SEARCH -> state.animeFilter.searchAnimeFilter.type
                    },
                    onAnimeTypeChanged = { selectedType: AnimeType? ->
                        val modifiedAnimeType: AnimeFilter =
                            when (state.displayType) {
                                DisplayType.POPULAR -> state.animeFilter.copy(
                                    popularAnimeFilter = state.animeFilter.popularAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )

                                DisplayType.AIRING -> state.animeFilter.copy(
                                    airingAnimeFilter = state.animeFilter.airingAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )

                                DisplayType.UPCOMING -> state.animeFilter.copy(
                                    upcomingAnimeFilter = state.animeFilter.upcomingAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )

                                DisplayType.SEARCH -> state.animeFilter.copy(
                                    searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )
                            }
                        onEvent(
                            OnAnimeFilterChanged(modifiedAnimeType)
                        )
                    }
                )
            }

            if (state.displayType == DisplayType.POPULAR || state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeRatingFilterMenu(
                        selectedAnimeRating =
                        when (state.displayType) {
                            DisplayType.POPULAR -> state.animeFilter.popularAnimeFilter.rating
                            DisplayType.SEARCH -> state.animeFilter.searchAnimeFilter.rating
                            else -> null
                        }, onAnimeRatingSelected = { selectedRating: AnimeRating? ->
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.POPULAR -> state.animeFilter.copy(
                                    popularAnimeFilter = state.animeFilter.popularAnimeFilter.copy(
                                        rating = selectedRating
                                    )
                                )

                                DisplayType.SEARCH -> state.animeFilter.copy(
                                    searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                        rating = selectedRating
                                    )
                                )

                                else -> null
                            }

                            modifiedFilter?.let { filter ->
                                onEvent(OnAnimeFilterChanged(filter))
                            }
                        }
                    )
                }
            }

            if (state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeStatusFilterMenu(
                        selectedAnimeStatus = state.animeFilter.searchAnimeFilter.status,
                        onAnimeStatusChanged = { selectedStatus: AnimeStatus? ->
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.animeFilter.copy(
                                    searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                        status = selectedStatus
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let { filter ->
                                onEvent(OnAnimeFilterChanged(filter))
                            }
                        }
                    )
                }
            }

            if (state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeSortFilterMenu(
                        selectedAnimeSort = state.animeFilter.searchAnimeFilter.sort,
                        onAnimeSortChanged = { selectedSort: AnimeSort ->
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.animeFilter.copy(
                                    searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                        sort = selectedSort
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let { filter ->
                                onEvent(OnAnimeFilterChanged(filter))
                            }
                        }
                    )
                }
            }

            if (state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeGenreFilterMenu(
                        selectedAnimeGenres = state.animeFilter.searchAnimeFilter.genres.toList(),
                        onAnimeGenreAdded = { selectedGenre: AnimeGenre ->
                            val modifiedGenre = state.animeFilter
                                .searchAnimeFilter
                                .genres
                                .toMutableList()
                            modifiedGenre.add(selectedGenre)
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.animeFilter.copy(
                                    searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                        genres = modifiedGenre.toList()
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let { filter ->
                                onEvent(OnAnimeFilterChanged(filter))
                            }
                        },
                        onAnimeGenreRemoved = { selectedGenre: AnimeGenre ->
                            val modifiedGenre = state.animeFilter
                                .searchAnimeFilter
                                .genres
                                .toMutableList()
                            modifiedGenre.remove(selectedGenre)
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.animeFilter.copy(
                                    searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                        genres = modifiedGenre
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let { filter ->
                                onEvent(OnAnimeFilterChanged(filter))
                            }
                        },
                        onAnimeGenreCleared = {
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.animeFilter.copy(
                                    searchAnimeFilter = state.animeFilter.searchAnimeFilter.copy(
                                        genres = emptyList()
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let { filter ->
                                onEvent(OnAnimeFilterChanged(filter))
                            }
                        }
                    )
                }
            }
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