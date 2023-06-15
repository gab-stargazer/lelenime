package com.lelestacia.lelenime.core.common.requestParam

enum class AnimeType {
    TV,
    MOVIE,
    OVA,
    SPECIAL,
    ONA,
    MUSIC
}

val ListOfAnimeType: List<AnimeType?> = listOf(
    null,
    AnimeType.TV,
    AnimeType.MOVIE,
    AnimeType.OVA,
    AnimeType.SPECIAL,
    AnimeType.ONA,
    AnimeType.MUSIC
)
