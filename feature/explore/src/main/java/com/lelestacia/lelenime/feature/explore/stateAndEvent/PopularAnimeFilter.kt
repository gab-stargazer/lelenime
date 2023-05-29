package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.core.common.requestParam.AnimeStatus
import com.lelestacia.lelenime.core.common.requestParam.AnimeType

data class PopularAnimeFilter(
    val animeType: AnimeType? = null,
    val animeStatus: AnimeStatus? = null
)
