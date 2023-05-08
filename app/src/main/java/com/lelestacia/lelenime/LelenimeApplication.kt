package com.lelestacia.lelenime

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
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
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.CharacterDetail
import com.lelestacia.lelenime.feature.collection.screen.CollectionScreen
import com.lelestacia.lelenime.feature.collection.screen.CollectionScreenViewModel
import com.lelestacia.lelenime.feature.detail.screen.detailAnime.DetailScreen
import com.lelestacia.lelenime.feature.detail.screen.detailAnime.DetailAnimeViewModel
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

    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route

    LaunchedEffect(key1 = currentRoute, block = {
        Timber.d("Current route is $currentRoute")
        Timber.d("Current backstack: ${navController.currentBackStack.value.map { it.destination.route }}")
    })

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

            ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
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

                        uiController.setStatusBarColor(
                            color = MaterialTheme.colorScheme.background,
                            darkIcons = darkIcons
                        )

                        ExplorationScreen(
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
                                    ) {
                                        restoreState = true
                                    }
                                }
                            },
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
                            navController = navController,
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
                        val configuration: Configuration = LocalConfiguration.current
                        val screenHeight: Dp = configuration.screenHeightDp.dp
                        val oneThirdHeight: Dp = screenHeight / 3

                        val characterID = navBackstack.arguments?.getInt("mal_id") ?: 0
                        val viewModel: DetailCharacterViewModel = hiltViewModel()
                        LaunchedEffect(key1 = Unit, block = {
                            Timber.d("Fetching Character with MAL ID $characterID")
                            viewModel.fetchDetailCharacter(characterID = characterID)
                        })
                        val characterResource by viewModel.characterResource.collectAsStateWithLifecycle()
                        Surface(
                            modifier = Modifier.animateContentSize()
                        ) {
                            when (characterResource) {
                                is Resource.Error -> {}
                                Resource.Loading -> {
                                    Box(
                                        modifier = Modifier
                                            .height(oneThirdHeight)
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }

                                Resource.None -> Unit
                                is Resource.Success -> DetailCharacterScreen(
                                    characterResource.data as CharacterDetail
                                )
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
                                is Resource.Success -> SynopsisScreen(synopsis = (animeResource.data as Anime).synopsis as String)
                                else -> Unit
                            }
                        }
                    }
                }
            }
        }
    }
}