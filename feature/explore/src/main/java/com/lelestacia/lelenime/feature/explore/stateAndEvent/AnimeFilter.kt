package com.lelestacia.lelenime.feature.explore.stateAndEvent

import com.lelestacia.lelenime.feature.explore.component.filter.AiringAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.PopularAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.SearchAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.UpcomingAnimeFilter

data class AnimeFilter(
    val popularAnimeFilter: PopularAnimeFilter = PopularAnimeFilter(),
    val airingAnimeFilter: AiringAnimeFilter = AiringAnimeFilter(),
    val upcomingAnimeFilter: UpcomingAnimeFilter = UpcomingAnimeFilter(),
    val searchAnimeFilter: SearchAnimeFilter = SearchAnimeFilter()
)
