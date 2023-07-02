package com.lelestacia.lelenime.feature.explore.component.displayType

/**
 * Enum class representing different types of display options for exploration.
 * Use these display types to specify the context for filtering and showing anime content.
 *
 * Possible display types are:
 * - [POPULAR]: Display popular anime content.
 * - [AIRING]: Display currently airing anime content.
 * - [UPCOMING]: Display upcoming anime content.
 * - [SEARCH]: Display search results for anime content.
 */
enum class DisplayType {
    POPULAR, AIRING, UPCOMING, SEARCH
}

/**
 * List of available display types for exploration.
 * Use these display types to specify the context for filtering and showing anime content.
 * It contains [POPULAR], [AIRING], and [UPCOMING] display types.
 */
val ExplorationDisplayType: List<DisplayType> = listOf(
    DisplayType.POPULAR,
    DisplayType.AIRING,
    DisplayType.UPCOMING
)
