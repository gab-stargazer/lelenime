package com.lelestacia.lelenime.core.common.requestParam

enum class AnimeRating(val query: String, val title: String) {
    G(query = "g", title = "G - All Ages"),
    PG(query = "pg", title = "PG - Children"),
    PG_13(query = "pg13", title = "PG-13 - Teens 13 or older"),
    R_17(query = "r17", title = "R-17+ (violence & profanity"),
    R(query = "r", title = "R+ - Mild Nudity")
}
