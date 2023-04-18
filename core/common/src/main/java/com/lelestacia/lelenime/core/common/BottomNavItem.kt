package com.lelestacia.lelenime.core.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.ui.graphics.vector.ImageVector
import com.lelestacia.lelenime.core.common.route.Screen

data class BottomNavItem(
    val title: String,
    val iconActive: ImageVector,
    val iconInactive: ImageVector,
    val screen: Screen
)

val navItem: List<BottomNavItem> = listOf(
    BottomNavItem(
        title = "Explore",
        iconActive = Icons.Filled.Explore,
        iconInactive = Icons.Outlined.Explore,
        screen = Screen.Explore
    ),
    BottomNavItem(
        title = "Collection",
        iconActive = Icons.Filled.CollectionsBookmark,
        iconInactive = Icons.Outlined.CollectionsBookmark,
        screen = Screen.Collection
    ),
    BottomNavItem(
        title = "More",
        iconActive = Icons.Filled.MoreHoriz,
        iconInactive = Icons.Outlined.MoreHoriz,
        screen = Screen.More
    )
)
