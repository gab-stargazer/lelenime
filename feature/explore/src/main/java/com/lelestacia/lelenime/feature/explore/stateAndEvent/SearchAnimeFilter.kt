package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.core.common.request.param.AnimeGenre
import com.lelestacia.lelenime.core.common.request.param.AnimeRating
import com.lelestacia.lelenime.core.common.request.param.AnimeSort
import com.lelestacia.lelenime.core.common.request.param.AnimeStatus
import com.lelestacia.lelenime.core.common.request.param.AnimeType

data class SearchAnimeFilter(
    val type: AnimeType? = null,
    val status: AnimeStatus? = null,
    val rating: AnimeRating? = null,
    val sort: AnimeSort = AnimeSort.ASC,
    val genres: List<AnimeGenre> = emptyList(),
)
