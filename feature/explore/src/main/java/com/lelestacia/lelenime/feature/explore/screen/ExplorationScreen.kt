package com.lelestacia.lelenime.feature.explore.screen

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
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
import com.lelestacia.lelenime.feature.explore.component.ExplorationBottomSheet
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.stateAndEvent.AnimeFilter
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreBottomSheetState
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState
import com.lelestacia.lelenime.feature.explore.stateAndEvent.SearchBarEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.SearchBarState
import timber.log.Timber

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ExplorationScreen(
    windowSize: WindowSizeClass,
    screenState: ExploreScreenState,
    appliedAnimeFilter: AnimeFilter,
    currentAnimeFilter: AnimeFilter,
    isSearching: Boolean,
    onSearchingStateChanged: (Boolean) -> Unit,
    searchQuery: String,
    onSearch: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onEvent: (ExploreScreenEvent) -> Unit,
    onAnimeClicked: (Anime) -> Unit,
    onErrorParsingRequest: (Throwable) -> String,
    modifier: Modifier = Modifier,
    searchBarState: SearchBarState,
    onRequestFocus: (Boolean) -> Unit,
) {
    val pagingAnime: LazyPagingItems<Anime> = screenState.anime.collectAsLazyPagingItems()
    val listOfLazyGridState: Map<DisplayType, LazyGridState> = mapOf(
        Pair(DisplayType.POPULAR, rememberLazyGridState()),
        Pair(DisplayType.AIRING, rememberLazyGridState()),
        Pair(DisplayType.UPCOMING, rememberLazyGridState())
    )
    val listOfLazyListState: Map<DisplayType, LazyListState> = mapOf(
        Pair(DisplayType.POPULAR, rememberLazyListState()),
        Pair(DisplayType.AIRING, rememberLazyListState()),
        Pair(DisplayType.UPCOMING, rememberLazyListState())
    )

    val lazyGridState = listOfLazyGridState[screenState.displayType] ?: rememberLazyGridState()
    val lazyListState = listOfLazyListState[screenState.displayType] ?: rememberLazyListState()

    val modalBottomSheetState = rememberModalBottomSheetState()
    var isFilterMenuOpened by rememberSaveable {
        mutableStateOf(false)
    }

    val sheetScaffoldState = rememberBottomSheetScaffoldState()

    var selectedCategory by rememberSaveable {
        mutableStateOf(0)
    }

    val viewInfo = LocalView.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = searchBarState.isActive, block = {
        onRequestFocus(searchBarState.isActive)
    })


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
                    items(searchBarState.recentlySearched) {
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
                                text = it,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(onClick = {
                                onEvent(SearchBarEvent.OnSearchQueryChanged(it))
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
            DisplayedAnimeMenu(
                state = screenState,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
            Divider()
            when (val refreshing = pagingAnime.loadState.refresh) {
                is LoadState.Error -> {
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
                    //  Reset the state position
                    LaunchedEffect(key1 = Unit, block = {
                        listOfLazyGridState[screenState.displayType]?.scrollToItem(
                            index = 0,
                            scrollOffset = 0
                        )
                        listOfLazyListState[screenState.displayType]?.scrollToItem(
                            index = 0,
                            scrollOffset = 0
                        )
                    })

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
                                Text(
                                    text = stringResource(
                                        id = R.string.anime_not_found,
                                        screenState.headerScreenState.searchedAnimeTitle
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 24.dp)
                                )
                            }
                            return@Scaffold
                        }

                        if (screenState.displayStyle == DisplayStyle.LIST) {
                            LazyListAnime(
                                lazyListState = lazyListState,
                                pagingAnime = pagingAnime,
                                onAnimeClicked = onAnimeClicked,
                                modifier = Modifier
                                    .matchParentSize()
                                    .testTag("explore:scrollAnime")
                            )
                        } else {
                            LazyGridAnime(
                                windowSize = windowSize,
                                lazyGridState = lazyGridState,
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
                ModalBottomSheet(
                    sheetState = modalBottomSheetState,
                    onDismissRequest = { isFilterMenuOpened = !isFilterMenuOpened },
                    content = {
                        ExplorationBottomSheet(
                            onEvent = onEvent,
                            state = ExploreBottomSheetState(
                                displayType = screenState.displayType,
                                animeFilter = currentAnimeFilter
                            )
                        )
                    }
                )
            }
        }


//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValue)
//        ) {
//            SearchBar(
//                query = searchBarState.query,
//                onQueryChange = { newQuery ->
//                    onEvent(SearchBarEvent.OnSearchQueryChanged(newQuery))
//                },
//                onSearch = { searchedQuery ->
//                    onEvent(SearchBarEvent.OnSearch(searchedQuery))
//                },
//                active = searchBarState.isActive,
//                onActiveChange = { newState ->
//                    onEvent(SearchBarEvent.OnStateChanged(newState))
//                },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = null
//                    )
//                },
//                placeholder = {
//                    Text(text = "Search Anime...")
//                }
//            ) {
//                LazyColumn {
//                    items(
//                        items = searchBarState.recentlySearched,
//                        key = { it },
//                        contentType = { it }
//                    ) { recentlySearched ->
//                        Column(
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(text = recentlySearched)
//                            Divider(modifier = Modifier.fillMaxWidth())
//                        }
//                    }
//                }
//            }
//
//            Divider(modifier = Modifier.fillMaxWidth())
//            when (val refreshing = pagingAnime.loadState.refresh) {
//                is LoadState.Error -> {
//                    Timber.e(refreshing.error)
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.spacedBy(
//                            space = 8.dp,
//                            alignment = Alignment.CenterVertically
//                        ),
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Text(text = onErrorParsingRequest(refreshing.error))
//                        Button(
//                            onClick = { pagingAnime.retry() },
//                            shape = RoundedCornerShape(4.dp)
//                        ) {
//                            Text(text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.retry))
//                        }
//                    }
//                    return@BottomSheetScaffold
//                }
//
//                LoadState.Loading -> {
//                    //  Reset the state position
//                    LaunchedEffect(key1 = Unit, block = {
//                        listOfLazyGridState[screenState.displayType]?.scrollToItem(
//                            index = 0,
//                            scrollOffset = 0
//                        )
//                        listOfLazyListState[screenState.displayType]?.scrollToItem(
//                            index = 0,
//                            scrollOffset = 0
//                        )
//                    })
//
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        CircularProgressIndicator(
//                            color = MaterialTheme.colorScheme.primary,
//                            modifier = Modifier.testTag("explore:loading")
//                        )
//                    }
//                    return@BottomSheetScaffold
//                }
//
//                is LoadState.NotLoading -> {
//                    Box(
//                        contentAlignment = Alignment.BottomEnd,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .weight(1f)
//                    ) {
//                        if (pagingAnime.itemCount == 0 && screenState.displayType == DisplayType.SEARCH) {
//                            Column(
//                                verticalArrangement = Arrangement.Center,
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                modifier = Modifier.matchParentSize()
//                            ) {
//                                Text(
//                                    text = stringResource(
//                                        id = R.string.anime_not_found,
//                                        screenState.headerScreenState.searchedAnimeTitle
//                                    ),
//                                    textAlign = TextAlign.Center,
//                                    modifier = Modifier.padding(horizontal = 24.dp)
//                                )
//                            }
//                            return@BottomSheetScaffold
//                        }
//
//                        if (screenState.displayStyle == DisplayStyle.LIST) {
//                            LazyListAnime(
//                                lazyListState = lazyListState,
//                                pagingAnime = pagingAnime,
//                                onAnimeClicked = onAnimeClicked,
//                                modifier = Modifier
//                                    .matchParentSize()
//                                    .testTag("explore:scrollAnime")
//                            )
//                        } else {
//                            LazyGridAnime(
//                                windowSize = windowSize,
//                                lazyGridState = lazyGridState,
//                                pagingAnime = pagingAnime,
//                                displayStyle = screenState.displayStyle,
//                                onAnimeClicked = onAnimeClicked,
//                                modifier = Modifier
//                                    .matchParentSize()
//                                    .testTag("explore:scrollAnime")
//                            )
//                        }
//
//                        ExtendedFloatingActionButton(
//                            onClick = {
//                                isFilterMenuOpened = !isFilterMenuOpened
//                            },
//                            modifier = Modifier.padding(16.dp),
//                            text = {
//                                Text(text = "Filter")
//                            },
//                            icon = {
//                                Icon(
//                                    imageVector = Icons.Default.FilterList,
//                                    contentDescription = null
//                                )
//                            }
//                        )
//                    }
//                }
//            }
//        }
//

    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun PreviewExplorationScreen() {
    ExplorationScreen(
        windowSize = WindowSizeClass.calculateFromSize(DpSize.Zero),
        screenState = ExploreScreenState(),
        appliedAnimeFilter = AnimeFilter(),
        currentAnimeFilter = AnimeFilter(),
        onEvent = {},
        onAnimeClicked = {},
        onErrorParsingRequest = { return@ExplorationScreen "" },
        isSearching = false,
        onSearchingStateChanged = {},
        searchQuery = "",
        onSearch = {},
        onSearchQueryChanged = {},
        searchBarState = SearchBarState(),
        onRequestFocus = {}
    )
}
