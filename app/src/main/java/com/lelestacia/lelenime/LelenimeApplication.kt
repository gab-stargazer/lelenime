package com.lelestacia.lelenime

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.systemuicontroller.SystemUiController
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.common.route.Screen
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.CharacterDetail
import com.lelestacia.lelenime.feature.collection.screen.CollectionScreen
import com.lelestacia.lelenime.feature.collection.screen.CollectionScreenViewModel
import com.lelestacia.lelenime.feature.detail.screen.detailAnime.DetailAnimeViewModel
import com.lelestacia.lelenime.feature.detail.screen.detailAnime.DetailScreen
import com.lelestacia.lelenime.feature.detail.screen.detailCharacter.DetailCharacterScreen
import com.lelestacia.lelenime.feature.detail.screen.detailCharacter.DetailCharacterViewModel
import com.lelestacia.lelenime.feature.detail.screen.fullSynopsis.SynopsisScreen
import com.lelestacia.lelenime.feature.explore.screen.ExplorationScreen
import com.lelestacia.lelenime.feature.explore.screen.ExplorationScreenViewModel
import com.lelestacia.lelenime.feature.more.screen.about.AboutScreen
import com.lelestacia.lelenime.feature.more.screen.more.MoreScreen
import com.lelestacia.lelenime.feature.more.screen.settings.SettingScreen
import com.lelestacia.lelenime.feature.more.screen.settings.SettingViewModel
import com.lelestacia.lelenime.ui.component.LeleNimeBottomBar
import com.lelestacia.lelenime.ui.component.LelenimeNavigationRail
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
fun LelenimeApplication(
    windowSize: WindowSizeClass,
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    uiController: SystemUiController,
    darkIcons: Boolean
) {
    val isCompactScreen = windowSize.widthSizeClass == WindowWidthSizeClass.Compact
    val scope = rememberCoroutineScope()

    Row {
        if (windowSize.widthSizeClass != WindowWidthSizeClass.Compact) {
            LelenimeNavigationRail(navController = navController)
        }
        Scaffold(
            bottomBar = {
                if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
                    LeleNimeBottomBar(navController = navController)
                }
            }
        ) { paddingValue ->

            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetShape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp
                )
            ) {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screen.Explore.route
                ) {
                    composable(
                        route = Screen.Explore.route,
                        enterTransition = {
                            if (isCompactScreen) {
                                when (initialState.destination.route) {
                                    Screen.Collection.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Right,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    Screen.More.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Left,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    else -> null
                                }
                            } else {
                                when (initialState.destination.route) {
                                    Screen.Collection.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Down,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    Screen.More.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Up,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    else -> null
                                }
                            }
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(500))
                        }
                    ) {
                        val viewModel = hiltViewModel<ExplorationScreenViewModel>()
                        val uiState by viewModel.explorationScreenState.collectAsStateWithLifecycle()
                        val appliedAnimeFilter by viewModel.appliedAnimeFilter.collectAsStateWithLifecycle()
                        val currentAnimeFilter by viewModel.currentAnimeFilter.collectAsStateWithLifecycle()

                        val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
                        val currentSearchQuery by viewModel.currentSearchQuery.collectAsStateWithLifecycle()
                        val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()

                        val searchBarState by viewModel.searchBarState.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = isSearching, block = {
                            Timber.d("Is searching now $isSearching")
                        })

                        uiController.setStatusBarColor(
                            color = MaterialTheme.colorScheme.background,
                            darkIcons = darkIcons
                        )

                        ExplorationScreen(
                            windowSize = windowSize,
                            screenState = uiState,
                            appliedAnimeFilter = appliedAnimeFilter,
                            currentAnimeFilter = currentAnimeFilter,
                            onEvent = viewModel::onEvent,
                            onErrorParsingRequest = viewModel::errorParsingRequest,
                            onAnimeClicked = { anime ->
                                scope.launch {
                                    viewModel
                                        .insertOrUpdateAnimeHistory(anime)
                                        .join()
                                    navController.navigate(
                                        route = Screen
                                            .DetailAnimeScreen
                                            .createRoute(anime.malID)
                                    ) {
                                        restoreState = true
                                    }
                                }
                            },
                            isSearching = isSearching,
                            onSearchingStateChanged = { viewModel.isSearching.value = it },
                            searchQuery = currentSearchQuery,
                            onSearch = { viewModel.searchQuery.update { it } },
                            onSearchQueryChanged = { viewModel.currentSearchQuery.update { it } },
                            searchBarState = searchBarState,
                            modifier = Modifier.padding(paddingValue)
                        )
                    }

                    composable(
                        route = Screen.Collection.route,
                        enterTransition = {
                            if (isCompactScreen) {
                                when (initialState.destination.route) {
                                    Screen.Explore.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Left,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    Screen.More.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Right,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    else -> null
                                }
                            } else {
                                when (initialState.destination.route) {
                                    Screen.Explore.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Up,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    Screen.More.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Down,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    else -> null
                                }
                            }
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(500))
                        }
                    ) {
                        val viewModel = hiltViewModel<CollectionScreenViewModel>()
                        val uiState by viewModel.collectionScreenState.collectAsStateWithLifecycle()

                        uiController.setStatusBarColor(
                            color = MaterialTheme.colorScheme.background,
                            darkIcons = darkIcons
                        )

                        CollectionScreen(
                            windowSize = windowSize,
                            screenState = uiState,
                            onEvent = viewModel::onEvent,
                            onAnimeClicked = { anime ->
                                scope.launch {
                                    viewModel
                                        .insertOrUpdateAnimeHistory(anime)
                                        .join()
                                    navController.navigate(
                                        route = Screen
                                            .DetailAnimeScreen
                                            .createRoute(anime.malID)
                                    )
                                }
                            },
                            modifier = Modifier.padding(paddingValue)
                        )
                    }

                    composable(
                        route = Screen.More.route,
                        enterTransition = {
                            if (isCompactScreen) {
                                when (initialState.destination.route) {
                                    Screen.Explore.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Right,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    Screen.Collection.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Left,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    else -> null
                                }
                            } else {
                                when (initialState.destination.route) {
                                    Screen.Explore.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Down,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    Screen.Collection.route -> slideIntoContainer(
                                        towards = AnimatedContentScope.SlideDirection.Up,
                                        animationSpec = tween(500)
                                    ) + fadeIn(
                                        animationSpec = tween(500)
                                    )

                                    else -> null
                                }
                            }
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(500))
                        }
                    ) {
                        uiController.setStatusBarColor(
                            color = MaterialTheme.colorScheme.background,
                            darkIcons = darkIcons
                        )

                        MoreScreen(
                            navController = navController,
                            modifier = Modifier.padding(paddingValue)
                        )
                    }

                    composable(route = Screen.About.route) {
                        uiController.setStatusBarColor(
                            color = MaterialTheme.colorScheme.background,
                            darkIcons = darkIcons
                        )

                        AboutScreen(
                            navController = navController
                        )
                    }

                    composable(route = Screen.Settings.route) {
                        uiController.setStatusBarColor(
                            color = MaterialTheme.colorScheme.background,
                            darkIcons = darkIcons
                        )

                        val viewModel = hiltViewModel<SettingViewModel>()
                        val state by viewModel.settingScreenState.collectAsStateWithLifecycle()

                        SettingScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            navController = navController
                        )
                    }

                    composable(
                        route = Screen.DetailAnimeScreen.route,
                        arguments = listOf(
                            navArgument(name = "mal_id") {
                                type = NavType.IntType
                            }
                        ),
                        enterTransition = {
                            if (isCompactScreen) {
                                slideIntoContainer(
                                    towards = AnimatedContentScope.SlideDirection.Up,
                                    animationSpec = tween(500)
                                ) + fadeIn(
                                    animationSpec = tween(500)
                                )
                            } else {
                                slideIntoContainer(
                                    towards = AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(500)
                                ) + fadeIn(
                                    animationSpec = tween(500)
                                )
                            }
                        },
                        exitTransition = {
                            if (isCompactScreen) {
                                slideOutOfContainer(
                                    towards = AnimatedContentScope.SlideDirection.Down,
                                    animationSpec = tween(500)
                                ) + fadeOut(
                                    animationSpec = tween(500)
                                )
                            } else {
                                slideOutOfContainer(
                                    towards = AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(500)
                                ) + fadeOut(
                                    animationSpec = tween(500)
                                )
                            }
                        }
                    ) { navBackstack ->
                        val animeID = navBackstack.arguments?.getInt("mal_id") ?: 0
                        val viewModel = hiltViewModel<DetailAnimeViewModel>()

                        LaunchedEffect(key1 = Unit) {
                            viewModel.initiateView(animeID)
                        }

                        val animeResource by viewModel.anime.collectAsStateWithLifecycle()
                        val characterResource by viewModel.characters.collectAsStateWithLifecycle()

                        Surface(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            when (animeResource) {
                                is Resource.Success -> DetailScreen(
                                    navController = navController,
                                    animeID = animeID,
                                    anime = animeResource.data as Anime,
                                    charactersResource = characterResource,
                                    getCharactersByAnimeID = viewModel::getCharactersByAnimeID,
                                    updateFavoriteByAnimeID = viewModel::updateAnimeByAnimeID
                                )

                                else -> Unit
                            }
                        }
                    }

                    bottomSheet(
                        route = Screen.DetailCharacterScreen.route,
                        arguments = listOf(
                            navArgument(
                                name = "mal_id"
                            ) {
                                type = NavType.IntType
                            }
                        )
                    ) { navBackstack ->

                        val characterID = navBackstack.arguments?.getInt("mal_id") ?: 0
                        val viewModel: DetailCharacterViewModel = hiltViewModel()
                        LaunchedEffect(key1 = Unit, block = {
                            Timber.d("Fetching Character with MAL ID $characterID")
                            viewModel.fetchDetailCharacter(characterID = characterID)
                        })
                        val characterResource by viewModel.characterResource.collectAsStateWithLifecycle()
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .animateContentSize()
                        ) {
                            when (characterResource) {
                                Resource.Loading -> {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(all = MaterialTheme.spacing.large)
                                            .padding(top = MaterialTheme.spacing.large)
                                    ) {
                                        Text(text = "Fetching Character Detail")
                                        LinearProgressIndicator()
                                    }
                                }

                                is Resource.Success -> {
                                    DetailCharacterScreen(characterResource.data as CharacterDetail)
                                }

                                is Resource.Error -> {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(all = MaterialTheme.spacing.large)
                                            .padding(top = MaterialTheme.spacing.large)
                                    ) {
                                        Text(
                                            text = characterResource.message
                                                ?: stringResource(id = com.lelestacia.lelenime.core.common.R.string.unknown_error)
                                        )
                                        Button(
                                            onClick = {
                                                viewModel.fetchDetailCharacter(characterID)
                                            }
                                        ) {
                                            Text(text = "Try Again", textAlign = TextAlign.Center)
                                        }
                                    }
                                }

                                else -> Unit
                            }
                        }
                    }

                    bottomSheet(
                        route = Screen.FullSynopsisScreen.route,
                        arguments = listOf(
                            navArgument(
                                name = "mal_id"
                            ) {
                                type = NavType.IntType
                            }
                        )
                    ) { navBackstack ->
                        val animeID = navBackstack.arguments?.getInt("mal_id") ?: 0
                        val viewModel = hiltViewModel<DetailAnimeViewModel>()

                        LaunchedEffect(key1 = Unit) {
                            viewModel.initiateView(animeID)
                        }

                        val animeResource by viewModel.anime.collectAsStateWithLifecycle()
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .animateContentSize()
                        ) {
                            when (animeResource) {
                                is Resource.Success -> {
                                    val animeSynopsis = (animeResource.data as Anime)
                                        .synopsis as String
                                    SynopsisScreen(synopsis = animeSynopsis)
                                }

                                else -> Unit
                            }
                        }
                    }
                }
            }
        }
    }
}
