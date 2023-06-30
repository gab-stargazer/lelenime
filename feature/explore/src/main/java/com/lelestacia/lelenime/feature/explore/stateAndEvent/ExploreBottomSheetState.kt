package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType

data class ExploreBottomSheetState(
    val displayType: DisplayType = DisplayType.POPULAR,
    val currentAnimeFilter: AnimeFilter = AnimeFilter(),
    val appliedAnimeFilter: AnimeFilter = AnimeFilter()
)
