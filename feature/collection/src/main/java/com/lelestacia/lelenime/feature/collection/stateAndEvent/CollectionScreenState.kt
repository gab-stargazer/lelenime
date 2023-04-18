package com.lelestacia.lelenime.feature.collection.stateAndEvent

import androidx.paging.PagingData
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.core.model.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CollectionScreenState(
    val displayStyle: DisplayStyle = DisplayStyle.CARD,
    val isDisplayStyleOptionOpened: Boolean = false,
    val anime: Flow<PagingData<Anime>> = flowOf()
)
