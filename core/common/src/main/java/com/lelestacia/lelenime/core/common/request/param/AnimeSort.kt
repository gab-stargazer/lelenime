package com.lelestacia.lelenime.core.common.request.param

enum class AnimeSort(val query: String, val title: String) {
    ASC("asc", "Ascending"),
    DESC("desc", "Descending")
}

val ListOfAnimeSort: List<AnimeSort> = listOf(
    AnimeSort.ASC,
    AnimeSort.DESC,
)