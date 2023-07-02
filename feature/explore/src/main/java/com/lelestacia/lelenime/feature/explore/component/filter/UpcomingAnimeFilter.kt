package com.lelestacia.lelenime.feature.explore.component.filter

import com.lelestacia.lelenime.core.common.request.param.AnimeType

/**
 * A data class representing the filter options for upcoming anime content.
 *
 * @param type The type of upcoming anime content to filter by. It is an optional parameter
 *             that can be set to null if no specific type filter is applied.
 */
data class UpcomingAnimeFilter(
    val type: AnimeType? = null
)
