package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

data class GenericPaginationResponse<T>(
    @SerializedName("data")
    val data: List<T>,
    @SerializedName("pagination")
    val pagination: PaginationResponse
)
