package com.lelestacia.lelenime.core.common.request.param

/**
 * Enum class representing different filters for popular anime.
 * Each filter has a [title] and a corresponding [query] value to be used in API requests.
 *
 * @property title The title of the popular anime filter.
 * @property query The query parameter to be used in API requests for the corresponding filter.
 */
enum class PopularAnimeFilter(val title: String, val query: String) {

    /**
     * Filter for currently airing anime.
     */
    AIRING(
        title = "Airing",
        query = "airing"
    ),

    /**
     * Filter for upcoming anime.
     */
    UPCOMING(
        title = "Upcoming",
        query = "upcoming"
    ),

    /**
     * Filter anime by popularity.
     */
    BY_POPULARITY(
        title = "By Popularity",
        query = "bypopularity"
    ),

    /**
     * Filter for favorite anime.
     */
    FAVORITE(
        title = "Favorite",
        query = "favorite"
    )
}

/**
 * A list of popular anime filters.
 * This list contains [PopularAnimeFilter] instances, including null elements.
 *
 * @see PopularAnimeFilter
 * @see PopularAnimeFilter.AIRING
 * @see PopularAnimeFilter.UPCOMING
 * @see PopularAnimeFilter.BY_POPULARITY
 * @see PopularAnimeFilter.FAVORITE
 *
 * @property ListOfPopularAnimeFilter The list of popular anime filters.
 */
val ListOfPopularAnimeFilter: List<PopularAnimeFilter?> = listOf(
    null,
    PopularAnimeFilter.AIRING,
    PopularAnimeFilter.UPCOMING,
    PopularAnimeFilter.BY_POPULARITY,
    PopularAnimeFilter.FAVORITE
)