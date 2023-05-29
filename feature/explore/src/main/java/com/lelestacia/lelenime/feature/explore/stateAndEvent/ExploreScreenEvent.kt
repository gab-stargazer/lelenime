package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.feature.explore.screen.DisplayType

sealed class ExploreScreenEvent {
    data class OnDisplayTypeChanged(val selectedType: DisplayType) : ExploreScreenEvent()
    object OnFilterOptionMenuChangedState : ExploreScreenEvent()
    object OnDisplayStyleOptionMenuStateChanged : ExploreScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : ExploreScreenEvent()
    data class OnSearchQueryChanged(val newSearchQuery: String) : ExploreScreenEvent()
    object OnStartSearching : ExploreScreenEvent()
    object OnSearch : ExploreScreenEvent()
    object OnStopSearching : ExploreScreenEvent()
    data class OnPopularAnimeFilterChanged(
        val popularAnimeFilter: PopularAnimeFilter
    ) : ExploreScreenEvent()

    data class OnUpcomingAnimeFilterChanged(
        val upcomingAnimeFilter: UpcomingAnimeFilter
    ) : ExploreScreenEvent()

    data class OnAnimeFilterChanged(
        val animeFilter: AnimeFilter
    ) : ExploreScreenEvent()

    object OnAnimeFilterApplied : ExploreScreenEvent()
}
