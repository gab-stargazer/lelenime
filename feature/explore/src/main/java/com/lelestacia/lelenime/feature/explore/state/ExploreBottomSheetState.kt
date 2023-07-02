package com.lelestacia.lelenime.feature.explore.state

import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType

data class ExploreBottomSheetState(
    val displayType: DisplayType = DisplayType.POPULAR,
    val currentAnimeFilterState: AnimeFilterState = AnimeFilterState(),
    val appliedAnimeFilterState: AnimeFilterState = AnimeFilterState()
)
