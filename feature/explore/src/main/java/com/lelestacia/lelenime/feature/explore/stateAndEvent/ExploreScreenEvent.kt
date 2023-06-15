package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType

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

sealed class SearchBarEvent : ExploreScreenEvent() {
    data class OnSearchQueryChanged(val query: String) : SearchBarEvent()
    data class OnStateChanged(val state: Boolean) : SearchBarEvent()
    data class OnSearch(val query: String) : SearchBarEvent()
}


/**
 * Sealed class representing events related to the bottom sheet in the Explore Screen.
 * Subclasses of [BottomSheetEvent] encapsulate specific events that can occur in the bottom sheet.
 */
sealed class BottomSheetEvent : ExploreScreenEvent()

/**
 * Event indicating that the display type in the bottom sheet has changed.
 *
 * @property displayType The new [DisplayType] selected in the bottom sheet.
 */
data class OnDisplayTypeChanged(val displayType: DisplayType) : BottomSheetEvent()

/**
 * Event indicating that the anime filter in the bottom sheet has changed.
 *
 * @property animeFilter The new [AnimeFilter] selected in the bottom sheet.
 */
data class OnAnimeFilterChanged(val animeFilter: AnimeFilter) : BottomSheetEvent()
