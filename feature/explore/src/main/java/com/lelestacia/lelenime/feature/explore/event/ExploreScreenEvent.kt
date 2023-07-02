package com.lelestacia.lelenime.feature.explore.event

import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.state.AnimeFilterState

/**
 * Sealed class representing various events that can occur on the Explore Screen.
 * It serves as a container for different types of events that can be handled by the Explore Screen.
 */
sealed class ExploreScreenEvent {

    /**
     * Data class representing the event when the display type is changed on the Explore Screen.
     *
     * @param selectedType The [DisplayType] representing the newly selected display type.
     */
    data class OnDisplayTypeChanged(val selectedType: DisplayType) : ExploreScreenEvent()
}

/**
 * Sealed class representing events related to the search bar on the ExploreScreen.
 * Subclasses of [SearchBarEvent] represent specific events that can occur with the search bar.
 */
sealed class SearchBarEvent : ExploreScreenEvent() {

    /**
     * Event indicating that the search query in the search bar has changed.
     *
     * @property newSearchQuery The new search query entered by the user.
     */
    data class OnSearchQueryChanged(val newSearchQuery: String) : SearchBarEvent()

    /**
     * Event indicating that the state of the search bar has changed.
     *
     * @property newState The new state of the search bar, represented as a boolean value.
     */
    data class OnStateChanged(val newState: Boolean) : SearchBarEvent()

    /**
     * Event indicating that a search is being performed from the search bar.
     *
     * @property query The search query entered by the user for performing the search.
     */
    data class OnSearch(val query: String) : SearchBarEvent()
}

/**
 * Sealed class representing events related to the bottom sheet in the Explore Screen.
 * Subclasses of [BottomSheetEvent] encapsulate specific events that can occur in the bottom sheet.
 */
sealed class BottomSheetEvent : ExploreScreenEvent() {

    /**
     * Event indicating that the display type in the bottom sheet has changed.
     *
     * @property displayType The new [DisplayType] selected in the bottom sheet.
     */
    data class OnDisplayTypeChanged(val displayType: DisplayType) : BottomSheetEvent()

    /**
     * Event indicating that the anime filter in the bottom sheet has changed.
     *
     * @property animeFilterState The new [AnimeFilterState] selected in the bottom sheet.
     */
    data class OnAnimeFilterChanged(val animeFilterState: AnimeFilterState) : BottomSheetEvent()

    /**
     * Event indicating that the anime filter in the bottom sheet has been applied.
     * This event is typically triggered when the user confirms the selection of the anime filter.
     */
    object OnAnimeFilterApplied : BottomSheetEvent()
}
