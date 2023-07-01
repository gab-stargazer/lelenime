package com.lelestacia.lelenime.feature.explore.component.filter

import com.lelestacia.lelenime.core.common.request.param.AnimeRating
import com.lelestacia.lelenime.core.common.request.param.AnimeType
import com.lelestacia.lelenime.core.common.request.param.PopularAnimeFilter

/**
 * A data class representing the filter options for popular anime content.
 *
 * @param type The type of anime content to filter by. It is an optional parameter
 *             that can be set to null if no specific type filter is applied.
 * @param filter The status of anime content to filter by. It is an optional parameter
 *               that can be set to null if no specific status filter is applied.
 * @param rating The rating of anime content to filter by. It is an optional parameter
 *               that can be set to null if no specific rating filter is applied.
 */
data class PopularAnimeFilter(
    val type: AnimeType? = null,
    val filter: PopularAnimeFilter? = null,
    val rating: AnimeRating? = null,
)
