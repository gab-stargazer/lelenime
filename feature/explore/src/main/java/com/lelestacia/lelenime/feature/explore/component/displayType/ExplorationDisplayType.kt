package com.lelestacia.lelenime.feature.explore.component.displayType

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector
import com.lelestacia.lelenime.feature.explore.screen.DisplayType

data class ExplorationDisplayType(
    val displayType: DisplayType,
    val icon: ImageVector
)

val LelenimeExplorationDisplayType = listOf(
    ExplorationDisplayType(
        displayType = DisplayType.POPULAR,
        icon = Icons.Default.Favorite
    ),
    ExplorationDisplayType(
        displayType = DisplayType.AIRING,
        icon = Icons.Default.MovieFilter
    ),
    ExplorationDisplayType(
        displayType = DisplayType.UPCOMING,
        icon = Icons.Filled.Upcoming
    ),
    ExplorationDisplayType(
        displayType = DisplayType.SEARCH,
        icon = Icons.Filled.Search
    )
)
