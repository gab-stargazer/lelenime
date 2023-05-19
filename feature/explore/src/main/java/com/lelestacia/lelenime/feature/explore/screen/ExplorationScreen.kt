package com.lelestacia.lelenime.feature.explore.screen

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import com.lelestacia.lelenime.feature.explore.component.DashboardDisplayTypeHeader
import com.lelestacia.lelenime.feature.explore.component.DashboardSearchHeader
import com.lelestacia.lelenime.feature.explore.component.filter.PopularAnimeFilterSection
import com.lelestacia.lelenime.feature.explore.component.filter.UpcomingAnimeFilterSection
import com.lelestacia.lelenime.feature.explore.stateAndEvent.AnimeFilter
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExplorationScreen(
    windowSize: WindowSizeClass,
    screenState: ExploreScreenState,
    animeFilter: AnimeFilter,
    onEvent: (ExploreScreenEvent) -> Unit,
    onAnimeClicked: (Anime) -> Unit,
    onErrorParsingRequest: (Throwable) -> String,
    modifier: Modifier = Modifier
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

    Scaffold(
        topBar = {
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .animateContentSize()
            ) {
                DashboardSearchHeader(
                    screenState = screenState,
                    onEvent = onEvent
                )
                DashboardDisplayTypeHeader(
                    state = screenState,
                    onEvent = onEvent
                )
                AnimatedVisibility(
                    visible = screenState.displayType == DisplayType.POPULAR,
                    enter = fadeIn() + slideInVertically(tween()),
                    exit = fadeOut() + slideOutVertically(tween())
                ) {
                    PopularAnimeFilterSection(
                        popularAnimeFilter = animeFilter.popularAnimeFilter,
                        onPopularAnimeFilterChanged = {
                            onEvent(ExploreScreenEvent.OnPopularAnimeFilterChanged(it))
                        }
                    )
                }
                AnimatedVisibility(
                    visible = screenState.displayType == DisplayType.UPCOMING,
                    enter = fadeIn() + slideInVertically(tween()),
                    exit = fadeOut() + slideOutVertically(tween())
                ) {
                    UpcomingAnimeFilterSection(
                        upcomingAnimeFilter = animeFilter.upcomingAnimeFilter,
                        onUpcomingAnimeFilterChanged = {
                            onEvent(ExploreScreenEvent.OnUpcomingAnimeFilterChanged(it))
                        }
                    )
                }
                Divider()
            }
        },
        modifier = modifier
            .semantics {
                testTagsAsResourceId = true
            }
    ) { paddingValue ->
        when (val refreshing = pagingAnime.loadState.refresh) {
            is LoadState.Error -> {
                Timber.e(refreshing.error)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 8.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue)
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.testTag("explore:loading")
                    )
                }
                return@Scaffold
            }

            is LoadState.NotLoading -> {
                if (pagingAnime.itemCount == 0 && screenState.displayType == DisplayType.SEARCH) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValue)
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
                        modifier = Modifier
                            .padding(paddingValue)
                            .testTag("explore:scrollAnime"),
                        onAnimeClicked = onAnimeClicked
                    )
                } else {
                    LazyGridAnime(
                        windowSize = windowSize,
                        lazyGridState = lazyGridState,
                        pagingAnime = pagingAnime,
                        displayStyle = screenState.displayStyle,
                        modifier = Modifier
                            .padding(paddingValue)
                            .testTag("explore:scrollAnime"),
                        onAnimeClicked = onAnimeClicked
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun PreviewExplorationScreen() {
    ExplorationScreen(
        windowSize = calculateWindowSizeClass(activity = Activity()),
        screenState = ExploreScreenState(),
        animeFilter = AnimeFilter(),
        onEvent = {},
        onAnimeClicked = {},
        onErrorParsingRequest = { return@ExplorationScreen "" }
    )
}
