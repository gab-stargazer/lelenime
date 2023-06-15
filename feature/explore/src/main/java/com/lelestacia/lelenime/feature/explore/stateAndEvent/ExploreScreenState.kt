package com.lelestacia.lelenime.feature.explore.stateAndEvent

import androidx.paging.PagingData
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.feature.explore.component.header.HeaderScreenState
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ExploreScreenState(
    val displayType: DisplayType = DisplayType.POPULAR,
    val displayStyle: DisplayStyle = DisplayStyle.CARD,
    val headerScreenState: HeaderScreenState = HeaderScreenState(),
    val anime: Flow<PagingData<Anime>> = flowOf()
)
