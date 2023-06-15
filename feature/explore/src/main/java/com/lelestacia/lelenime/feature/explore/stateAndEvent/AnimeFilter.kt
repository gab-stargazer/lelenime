package com.lelestacia.lelenime.feature.explore.stateAndEvent

data class AnimeFilter(
    val popularAnimeFilter: PopularAnimeFilter = PopularAnimeFilter(),
    val airingAnimeFilter: AiringAnimeFilter = AiringAnimeFilter(),
    val upcomingAnimeFilter: UpcomingAnimeFilter = UpcomingAnimeFilter(),
    val searchAnimeFilter: SearchAnimeFilter = SearchAnimeFilter()
)
