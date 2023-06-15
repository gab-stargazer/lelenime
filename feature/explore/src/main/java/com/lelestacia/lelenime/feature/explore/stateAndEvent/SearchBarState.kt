package com.lelestacia.lelenime.feature.explore.stateAndEvent

data class SearchBarState(
    val query: String = "",
    val isActive: Boolean = false,
    val recentlySearched: List<String> = emptyList()
)
