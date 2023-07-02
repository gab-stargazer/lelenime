package com.lelestacia.lelenime.feature.explore.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.request.composable.AnimeGenreFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeRatingFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeSortFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeStatusFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.AnimeTypeFilterMenu
import com.lelestacia.lelenime.core.common.request.composable.PopularAnimeFilter
import com.lelestacia.lelenime.core.common.request.param.AnimeGenre
import com.lelestacia.lelenime.core.common.request.param.AnimeRating
import com.lelestacia.lelenime.core.common.request.param.AnimeSort
import com.lelestacia.lelenime.core.common.request.param.AnimeStatus
import com.lelestacia.lelenime.core.common.request.param.AnimeType
import com.lelestacia.lelenime.core.common.request.param.PopularAnimeFilter
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.event.BottomSheetEvent
import com.lelestacia.lelenime.feature.explore.state.AnimeFilterState
import com.lelestacia.lelenime.feature.explore.state.ExploreBottomSheetState

/**
 * Composable function that displays an exploration bottom sheet containing various anime filter options.
 *
 * @param state The current state of the exploration bottom sheet, holding information about the applied and current anime filters.
 * @param onEvent Lambda function to handle events from the bottom sheet. It takes a [BottomSheetEvent] as a parameter and returns `Unit`.
 * @param onDismiss Lambda function to handle the dismissal of the bottom sheet. It does not take any parameters and returns `Unit`.
 * @param modifier Optional [Modifier] that can be used to adjust the layout or appearance of the bottom sheet.
 *
 * The [ExploreBottomSheet] composable displays a bottom sheet with anime filter options. The bottom sheet contains various filter menus,
 * such as anime type, rating, status, sort, and genres, based on the [DisplayType] of the anime list (popular, airing, upcoming, or search).
 *
 * The [state] parameter holds the current and applied anime filters. It helps to determine the selected options in the filter menus and indicates
 * whether there are any changes to apply.
 *
 * The [onEvent] lambda function is used to handle events from the bottom sheet, such as changes to the anime filter or when the user applies the filter.
 * It receives a [BottomSheetEvent] as a parameter, representing the event type, and it should be handled accordingly by the parent composable.
 *
 * The [onDismiss] lambda function is used to handle the dismissal of the bottom sheet. It should be invoked when the user confirms the application of
 * the anime filter or cancels the bottom sheet.
 *
 * Note: The `@OptIn(ExperimentalFoundationApi::class)` annotation is used to opt into experimental features required for the LazyColumn implementation.
 *
 * Example usage:
 * ```kotlin
 * ExplorationBottomSheet(
 *     state = exploreBottomSheetState,
 *     onEvent = { event ->
 *         when (event) {
 *             is BottomSheetEvent.OnAnimeFilterChanged -> {
 *                 // Handle anime filter changes
 *             }
 *             BottomSheetEvent.OnAnimeFilterApplied -> {
 *                 // Handle anime filter applied
 *             }
 *         }
 *     },
 *     onDismiss = {
 *         // Handle bottom sheet dismissal
 *     }
 * )
 * ```
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreBottomSheet(
    state: ExploreBottomSheetState,
    onEvent: (BottomSheetEvent) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * Represents a lambda function to handle changes in the anime filter.
     *
     * @param filter The new [AnimeFilterState] to be applied.
     */
    val onFilterChanged: (AnimeFilterState) -> Unit = { filter ->
        onEvent(BottomSheetEvent.OnAnimeFilterChanged(filter))
    }

    /**
     * Represents a lambda function to handle the application of the anime filter in the bottom sheet.
     * This lambda does not take any parameters and is typically used to indicate that the user has
     * confirmed the selection of the anime filter.
     */
    val onFilterApplied: () -> Unit = {
        onEvent(BottomSheetEvent.OnAnimeFilterApplied)
    }

    val configuration = LocalConfiguration.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                min = (configuration.screenHeightDp / 2).dp,
                max = configuration.screenHeightDp.dp
            )
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.medium
            ),
            contentPadding = PaddingValues(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.extraLarge
            ),
            modifier = Modifier.animateContentSize()
        ) {
            item {
                AnimeTypeFilterMenu(
                    selectedAnimeType = when (state.displayType) {
                        DisplayType.POPULAR -> state.currentAnimeFilterState.popularAnimeFilter.type
                        DisplayType.AIRING -> state.currentAnimeFilterState.airingAnimeFilter.type
                        DisplayType.UPCOMING -> state.currentAnimeFilterState.upcomingAnimeFilter.type
                        DisplayType.SEARCH -> state.currentAnimeFilterState.searchAnimeFilter.type
                    },
                    onAnimeTypeChanged = { selectedType: AnimeType? ->
                        val modifierAnimeFilterState: AnimeFilterState =
                            when (state.displayType) {
                                DisplayType.POPULAR -> state.currentAnimeFilterState.copy(
                                    popularAnimeFilter = state.currentAnimeFilterState.popularAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )

                                DisplayType.AIRING -> state.currentAnimeFilterState.copy(
                                    airingAnimeFilter = state.currentAnimeFilterState.airingAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )

                                DisplayType.UPCOMING -> state.currentAnimeFilterState.copy(
                                    upcomingAnimeFilter = state.currentAnimeFilterState.upcomingAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )

                                DisplayType.SEARCH -> state.currentAnimeFilterState.copy(
                                    searchAnimeFilter = state.currentAnimeFilterState.searchAnimeFilter.copy(
                                        type = selectedType
                                    )
                                )
                            }
                        onFilterChanged(modifierAnimeFilterState)
                    }
                )
            }

            if (state.displayType == DisplayType.POPULAR) {
                item {
                    PopularAnimeFilter(
                        selectedPopularAnimeFilter = state.currentAnimeFilterState.popularAnimeFilter.filter,
                        onPopularAnimeFilterChanged = { selectedPopularAnimeFilter: PopularAnimeFilter? ->
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.POPULAR -> state.currentAnimeFilterState.copy(
                                    popularAnimeFilter = state.currentAnimeFilterState.popularAnimeFilter.copy(
                                        filter = selectedPopularAnimeFilter
                                    )
                                )

                                else -> null
                            }

                            modifiedFilter?.let(onFilterChanged)
                        }
                    )
                }
            }

            if (state.displayType == DisplayType.POPULAR || state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeRatingFilterMenu(
                        selectedAnimeRating =
                        when (state.displayType) {
                            DisplayType.POPULAR -> state.currentAnimeFilterState.popularAnimeFilter.rating
                            DisplayType.SEARCH -> state.currentAnimeFilterState.searchAnimeFilter.rating
                            else -> null
                        },
                        onAnimeRatingSelected = { selectedRating: AnimeRating? ->
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.POPULAR -> state.currentAnimeFilterState.copy(
                                    popularAnimeFilter = state.currentAnimeFilterState.popularAnimeFilter.copy(
                                        rating = selectedRating
                                    )
                                )

                                DisplayType.SEARCH -> state.currentAnimeFilterState.copy(
                                    searchAnimeFilter = state.currentAnimeFilterState.searchAnimeFilter.copy(
                                        rating = selectedRating
                                    )
                                )

                                else -> null
                            }

                            modifiedFilter?.let(onFilterChanged)
                        }
                    )
                }
            }

            if (state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeStatusFilterMenu(
                        selectedAnimeStatus = state.currentAnimeFilterState.searchAnimeFilter.status,
                        onAnimeStatusChanged = { selectedStatus: AnimeStatus? ->
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.currentAnimeFilterState.copy(
                                    searchAnimeFilter = state.currentAnimeFilterState.searchAnimeFilter.copy(
                                        status = selectedStatus
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let(onFilterChanged)
                        }
                    )
                }
            }

            if (state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeSortFilterMenu(
                        selectedAnimeSort = state.currentAnimeFilterState.searchAnimeFilter.sort,
                        onAnimeSortChanged = { selectedSort: AnimeSort ->
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.currentAnimeFilterState.copy(
                                    searchAnimeFilter = state.currentAnimeFilterState.searchAnimeFilter.copy(
                                        sort = selectedSort
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let(onFilterChanged)
                        }
                    )
                }
            }

            if (state.displayType == DisplayType.SEARCH) {
                item {
                    AnimeGenreFilterMenu(
                        selectedAnimeGenres = state.currentAnimeFilterState.searchAnimeFilter.genres.toList(),
                        onAnimeGenreAdded = { selectedGenre: AnimeGenre ->
                            val modifiedGenre = state.currentAnimeFilterState
                                .searchAnimeFilter
                                .genres
                                .toMutableList()
                            modifiedGenre.add(selectedGenre)
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.currentAnimeFilterState.copy(
                                    searchAnimeFilter = state.currentAnimeFilterState.searchAnimeFilter.copy(
                                        genres = modifiedGenre.toList()
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let(onFilterChanged)
                        },
                        onAnimeGenreRemoved = { selectedGenre: AnimeGenre ->
                            val modifiedGenre = state.currentAnimeFilterState
                                .searchAnimeFilter
                                .genres
                                .toMutableList()
                            modifiedGenre.remove(selectedGenre)
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.currentAnimeFilterState.copy(
                                    searchAnimeFilter = state.currentAnimeFilterState.searchAnimeFilter.copy(
                                        genres = modifiedGenre
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let(onFilterChanged)
                        },
                        onAnimeGenreCleared = {
                            val modifiedFilter = when (state.displayType) {
                                DisplayType.SEARCH -> state.currentAnimeFilterState.copy(
                                    searchAnimeFilter = state.currentAnimeFilterState.searchAnimeFilter.copy(
                                        genres = emptyList()
                                    )
                                )

                                else -> null
                            }
                            modifiedFilter?.let(onFilterChanged)
                        }
                    )
                }
            }

            if (state.currentAnimeFilterState != state.appliedAnimeFilterState) {
                item {
                    Button(
                        onClick = {
                            onFilterApplied()
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.large)
                            .animateItemPlacement()
                    ) {
                        Text(text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.apply_anime_filter))
                    }
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
        ExploreBottomSheet(
            state = state,
            onEvent = { event ->
                state = when (event) {
                    is BottomSheetEvent.OnDisplayTypeChanged -> {
                        state.copy(
                            displayType = event.displayType
                        )
                    }

                    is BottomSheetEvent.OnAnimeFilterChanged -> {
                        state.copy(
                            currentAnimeFilterState = event.animeFilterState
                        )
                    }

                    BottomSheetEvent.OnAnimeFilterApplied -> {
                        state
                    }
                }
            },
            onDismiss = {}
        )
    }
}
