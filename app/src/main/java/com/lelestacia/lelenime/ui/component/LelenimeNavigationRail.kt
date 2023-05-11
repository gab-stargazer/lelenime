package com.lelestacia.lelenime.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lelestacia.lelenime.core.common.navItem
import com.lelestacia.lelenime.core.common.route.Screen
import com.lelestacia.lelenime.util.rootDestinations

@Composable
fun LelenimeNavigationRail(navController: NavHostController) {
    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route
    val shouldBeVisible = rootDestinations.contains(currentRoute ?: Screen.Explore.route)

    AnimatedVisibility(
        visible = shouldBeVisible,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 250)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 250)
        )
    ) {
        NavigationRail(
            header = {
                Image(
                    painter = painterResource(id = com.lelestacia.lelenime.core.common.R.drawable.lelenime),
                    contentDescription = "Lelenime Icon",
                    modifier = Modifier.size(48.dp)
                )
            }
        ) {
            navItem.map { navItem ->
                NavigationRailItem(
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
