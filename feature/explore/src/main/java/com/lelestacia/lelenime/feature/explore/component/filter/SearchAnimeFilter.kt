package com.lelestacia.lelenime.feature.explore.component.filter

import com.lelestacia.lelenime.core.common.request.param.AnimeGenre
import com.lelestacia.lelenime.core.common.request.param.AnimeRating
import com.lelestacia.lelenime.core.common.request.param.AnimeSort
import com.lelestacia.lelenime.core.common.request.param.AnimeStatus
import com.lelestacia.lelenime.core.common.request.param.AnimeType

/**
 * A data class representing the filter options for searching anime content.
 *
 * @param type The type of anime content to filter by. It is an optional parameter
 *             that can be set to null if no specific type filter is applied.
 * @param status The status of anime content to filter by. It is an optional parameter
 *               that can be set to null if no specific status filter is applied.
 * @param rating The rating of anime content to filter by. It is an optional parameter
 *               that can be set to null if no specific rating filter is applied.
 * @param sort The sorting order for the search results. It is an optional parameter
 *             with a default value of [AnimeSort.ASC] which represents ascending order.
 * @param genres The list of anime genres to filter by. It is an optional parameter
 *              with an empty list as the default value, which means no specific genre filter is applied.
 */
data class SearchAnimeFilter(
    val type: AnimeType? = null,
    val status: AnimeStatus? = null,
    val rating: AnimeRating? = null,
    val sort: AnimeSort = AnimeSort.ASC,
    val genres: List<AnimeGenre> = emptyList()
)
