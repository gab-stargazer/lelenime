package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.feature.explore.screen.DisplayType

data class ExploreBottomSheetState(
    val displayType: DisplayType = DisplayType.POPULAR,
    val animeFilter: AnimeFilter = AnimeFilter()
)
