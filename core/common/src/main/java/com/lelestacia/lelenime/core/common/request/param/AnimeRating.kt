package com.lelestacia.lelenime.core.common.request.param

enum class AnimeRating(val query: String, val title: String) {
    G(query = "g", title = "G - All Ages"),
    PG(query = "pg", title = "PG - Children"),
    PG_13(query = "pg13", title = "PG-13 - Teens 13 or older"),
    R(query = "r", title = "R+ - Mild Nudity"),
    R_17(query = "r17", title = "R-17+ - Violence & profanity")
}

val ListOfAnimeRatings: List<AnimeRating?> = listOf(
    null,
    AnimeRating.G,
    AnimeRating.PG,
    AnimeRating.PG_13,
    AnimeRating.R,
    AnimeRating.R_17,
)