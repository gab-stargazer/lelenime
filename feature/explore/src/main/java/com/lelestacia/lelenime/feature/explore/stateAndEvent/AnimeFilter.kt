package com.lelestacia.lelenime.feature.explore.stateAndEvent

data class AnimeFilter(
    val popularAnimeFilter: PopularAnimeFilter = PopularAnimeFilter(),
    val upcomingAnimeFilter: UpcomingAnimeFilter = UpcomingAnimeFilter()
)
