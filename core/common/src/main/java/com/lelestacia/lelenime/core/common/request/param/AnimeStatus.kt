package com.lelestacia.lelenime.core.common.request.param

enum class AnimeStatus {
    AIRING,
    COMPLETE,
    UPCOMING
}

val ListOfAnimeStatus: List<AnimeStatus?> = listOf(
    null,
    AnimeStatus.AIRING,
    AnimeStatus.COMPLETE,
    AnimeStatus.UPCOMING
)
