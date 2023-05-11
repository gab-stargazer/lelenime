package com.lelestacia.lelenime.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lelestacia.lelenime.core.common.navItem
import com.lelestacia.lelenime.util.rootDestinations

@Composable
fun LeleNimeBottomBar(navController: NavHostController) {
    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route
    val shouldBeVisible = rootDestinations.contains(currentRoute)

    AnimatedVisibility(
        visible = shouldBeVisible,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 250)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 250)
        )
    ) {
        NavigationBar {
            navItem.map { navItem ->
                NavigationBarItem(
                    selected = currentRoute == navItem.screen.route,
                    onClick = {
                        navController.navigate(route = navItem.title) {
                            popUpTo(id = navController.graph.startDestinationId) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector =
                            if (currentRoute == navItem.screen.route) {
                                navItem.iconActive
                            } else {
                                navItem.iconInactive
                            },
                            contentDescription = navItem.title
                        )
                    },
                    label = {
                        Text(text = navItem.title)
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}
