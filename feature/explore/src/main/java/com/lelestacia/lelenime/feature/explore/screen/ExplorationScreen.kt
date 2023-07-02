package com.lelestacia.lelenime.feature.explore.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.core.common.lazyAnime.LazyGridAnime
import com.lelestacia.lelenime.core.common.lazyAnime.LazyListAnime
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.feature.explore.R
import com.lelestacia.lelenime.feature.explore.component.DisplayedAnimeMenu
import com.lelestacia.lelenime.feature.explore.component.ExploreBottomSheet
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.event.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.event.SearchBarEvent
import com.lelestacia.lelenime.feature.explore.state.ExploreBottomSheetState
import com.lelestacia.lelenime.feature.explore.state.ExploreScreenState
import com.lelestacia.lelenime.feature.explore.state.SearchBarState
import timber.log.Timber

/**
 * Composable function representing the Explore Screen, displaying a list/grid of anime.
 *
 * @param windowSize The [WindowSizeClass] representing the current window size information.
 * @param screenState The current state of the Explore Screen, including the selected display type and style.
 * @param animeGridState A map containing the [LazyGridState] for each display type of the anime grid.
 * @param animeListState A map containing the [LazyListState] for each display type of the anime list.
 * @param bottomSheetState The state of the Explore Screen's bottom sheet, containing filter options.
 * @param searchBarState The state of the search bar, including query and active state.
 * @param modifier The [Modifier] for the root element of this composable.
 * @param onEvent The event handler for handling different [ExploreScreenEvent] events.
 * @param onAnimeClicked The callback function to be called when an anime item is clicked.
 * @param onErrorParsingRequest A function to handle the parsing of throwable errors and display an error message.
 * @param onRequestFocus The callback function to request or remove focus from the search bar.
 */
@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun ExplorationScreen(
    windowSize: WindowSizeClass,
    screenState: ExploreScreenState,
    animeGridState: Map<DisplayType, LazyGridState>,
    animeListState: Map<DisplayType, LazyListState>,
    bottomSheetState: ExploreBottomSheetState,
    searchBarState: SearchBarState,
    modifier: Modifier = Modifier,
    onEvent: (ExploreScreenEvent) -> Unit,
    onAnimeClicked: (Anime) -> Unit,
    onErrorParsingRequest: (Throwable) -> String,
    onRequestFocus: (Boolean) -> Unit
) {
    val pagingAnime: LazyPagingItems<Anime> = screenState.anime.collectAsLazyPagingItems()
    val modalBottomSheetState = rememberModalBottomSheetState()
    var isFilterMenuOpened by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = searchBarState.isActive) {
        onRequestFocus(searchBarState.isActive)
    }

    Scaffold(
        modifier = modifier.semantics {
            testTagsAsResourceId = true
        }
    ) { paddingValue ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchBar(
                    query = searchBarState.query,
                    onQueryChange = { newQuery ->
                        onEvent(SearchBarEvent.OnSearchQueryChanged(newQuery))
                    },
                    onSearch = { searchedQuery ->
                        onEvent(SearchBarEvent.OnSearch(searchedQuery))
                    },
                    active = searchBarState.isActive,
                    onActiveChange = { newState ->
                        onEvent(SearchBarEvent.OnStateChanged(newState))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    placeholder = {
                        Text(text = "Search Anime...")
                    },
                    trailingIcon = {
                        IconButton(onClick = { isFilterMenuOpened = !isFilterMenuOpened }) {
                            Icon(imageVector = Icons.Default.FilterList, contentDescription = null)
                        }
                    }
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(searchBarState.recentlySearched) { recentlySearchedAnimeTitle ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = MaterialTheme.spacing.large)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.History,
                                    contentDescription = null
                                )
                                Text(
                                    text = recentlySearchedAnimeTitle,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(onClick = {
                                    onEvent(
                                        SearchBarEvent.OnSearchQueryChanged(
                                            recentlySearchedAnimeTitle
                                        )
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowOutward,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }

            DisplayedAnimeMenu(
                state = screenState,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )

            Divider()

            when (val refreshing = pagingAnime.loadState.refresh) {
                is LoadState.Error -> {
                    // Display an error message and a retry button when an error occurs during data loading.
                    // The returned UI element is wrapped in a Scaffold.
                    Timber.e(refreshing.error)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            space = 8.dp,
                            alignment = Alignment.CenterVertically
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = onErrorParsingRequest(refreshing.error))
                        Button(
                            onClick = { pagingAnime.retry() },
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.retry))
                        }
                    }
                    return@Scaffold
                }

                LoadState.Loading -> {
                    // Display a loading indicator when data is being loaded.
                    // The returned UI element is wrapped in a Scaffold.
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(1f)
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.testTag("explore:loading")
                        )
                    }
                    return@Scaffold
                }

                is LoadState.NotLoading -> {
                    // Display the anime list when data loading is successful and there are items to show.
                    // The returned UI element is wrapped in a Scaffold.
                    Box(
                        contentAlignment = Alignment.BottomEnd,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        if (pagingAnime.itemCount == 0 && screenState.displayType == DisplayType.SEARCH) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.matchParentSize()
                            ) {
                                //  TODO("Fix this so it display latest search properly")
                                Text(
                                    text = stringResource(
                                        id = R.string.anime_not_found,
                                        if (searchBarState.recentlySearched.isEmpty()) {
                                            ""
                                        } else {
                                            searchBarState.recentlySearched.first()
                                        }
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 24.dp)
                                )
                            }
                            return@Scaffold
                        }

                        if (screenState.displayStyle == DisplayStyle.LIST) {
                            LazyListAnime(
                                lazyListState =
                                animeListState[screenState.displayType] ?: rememberLazyListState(),
                                pagingAnime = pagingAnime,
                                onAnimeClicked = onAnimeClicked,
                                modifier = Modifier
                                    .matchParentSize()
                                    .testTag("explore:scrollAnime")
                            )
                        } else {
                            LazyGridAnime(
                                windowSize = windowSize,
                                lazyGridState =
                                animeGridState[screenState.displayType] ?: rememberLazyGridState(),
                                pagingAnime = pagingAnime,
                                displayStyle = screenState.displayStyle,
                                onAnimeClicked = onAnimeClicked,
                                modifier = Modifier
                                    .matchParentSize()
                                    .testTag("explore:scrollAnime")
                            )
                        }
                    }
                }
            }

            if (isFilterMenuOpened) {
                val onDismissFilterMenu: () -> Unit = {
                    isFilterMenuOpened = !isFilterMenuOpened
                }

                ModalBottomSheet(
                    sheetState = modalBottomSheetState,
                    onDismissRequest = onDismissFilterMenu,
                    content = {
                        ExploreBottomSheet(
                            onEvent = onEvent,
                            state = bottomSheetState,
                            onDismiss = onDismissFilterMenu
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun PreviewExplorationScreen() {
    ExplorationScreen(
        windowSize = WindowSizeClass.calculateFromSize(DpSize.Zero),
        screenState = ExploreScreenState(),
        animeGridState = mapOf(),
        animeListState = mapOf(),
        bottomSheetState = ExploreBottomSheetState(),
        onEvent = {},
        onAnimeClicked = {},
        onErrorParsingRequest = { return@ExplorationScreen "" },
        searchBarState = SearchBarState()
    ) {}
}
