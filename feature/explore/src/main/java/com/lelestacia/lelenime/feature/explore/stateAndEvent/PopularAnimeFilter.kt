package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.core.common.request.param.AnimeRating
import com.lelestacia.lelenime.core.common.request.param.AnimeStatus
import com.lelestacia.lelenime.core.common.request.param.AnimeType

data class PopularAnimeFilter(
    val type: AnimeType? = null,
    val status: AnimeStatus? = null,
    val rating: AnimeRating? = null,
)
