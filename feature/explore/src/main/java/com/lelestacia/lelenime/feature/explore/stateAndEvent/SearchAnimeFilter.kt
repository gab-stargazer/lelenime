package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.core.common.requestParam.AnimeRating
import com.lelestacia.lelenime.core.common.requestParam.AnimeStatus
import com.lelestacia.lelenime.core.common.requestParam.AnimeType

data class SearchAnimeFilter(
    val animeType: AnimeType? = null,
    val animeStatus: AnimeStatus? = null,
    val animeRating: AnimeRating? = null
)
