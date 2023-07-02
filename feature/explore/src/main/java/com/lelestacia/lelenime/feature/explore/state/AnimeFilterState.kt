package com.lelestacia.lelenime.feature.explore.state

import com.lelestacia.lelenime.feature.explore.component.filter.AiringAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.PopularAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.SearchAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.UpcomingAnimeFilter

data class AnimeFilterState(
    val popularAnimeFilter: PopularAnimeFilter = PopularAnimeFilter(),
    val airingAnimeFilter: AiringAnimeFilter = AiringAnimeFilter(),
    val upcomingAnimeFilter: UpcomingAnimeFilter = UpcomingAnimeFilter(),
    val searchAnimeFilter: SearchAnimeFilter = SearchAnimeFilter()
)
