package com.lelestacia.lelenime.feature.explore.component.filter

import com.lelestacia.lelenime.core.common.request.param.AnimeType

/**
 * A data class representing the filter options for airing anime content.
 *
 * @param type The type of anime content to filter by. It is an optional parameter
 *             that can be set to null if no specific type filter is applied.
 */
data class AiringAnimeFilter(
    val type: AnimeType? = null
)
