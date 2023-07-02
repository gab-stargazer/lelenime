package com.lelestacia.lelenime.ui.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import com.lelestacia.lelenime.core.common.route.Screen
import com.lelestacia.lelenime.feature.explore.screen.ExplorationScreen
import com.lelestacia.lelenime.feature.explore.viewmodel.ExplorationScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.exploration(
    darkIcons: Boolean,
    isCompactScreen: Boolean,
    navController: NavController,
    onFocusRequest: (Boolean) -> Unit,
    paddingValue: PaddingValues,
    scope: CoroutineScope,
    uiController: SystemUiController,
    windowSize: WindowSizeClass,
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

        val displayStyle by viewModel.currentDisplayStyle.collectAsStateWithLifecycle()
        val displayType by viewModel.currentDisplayType.collectAsStateWithLifecycle()

        val animeGridState by viewModel.animeGridState.collectAsStateWithLifecycle()
        val animeListState by viewModel.animeListState.collectAsStateWithLifecycle()

        val searchBarState by viewModel.searchBarState.collectAsStateWithLifecycle()
        val bottomSheetState by viewModel.bottomSheetState.collectAsStateWithLifecycle()

        uiController.setStatusBarColor(
            color = MaterialTheme.colorScheme.background,
            darkIcons = darkIcons
        )

        ExplorationScreen(
            searchBarState = searchBarState,
            screenState = uiState,
            bottomSheetState = bottomSheetState,
            animeGridState = animeGridState,
            animeListState = animeListState,
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
            onErrorParsingRequest = viewModel::errorParsingRequest,
            onRequestFocus = onFocusRequest,
            modifier = Modifier.padding(paddingValue),
            windowSize = windowSize
        )
    }
}