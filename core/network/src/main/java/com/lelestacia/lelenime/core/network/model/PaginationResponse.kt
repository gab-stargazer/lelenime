package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean
)
