package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * A generic response class that contains a list of data of type [T] and pagination information.
 *
 * @property data The list of data of type [T].
 * @property pagination The pagination information of type [PaginationResponse].
 */
data class GenericPaginationResponse<T>(
    @SerializedName("data")
    val data: List<T>,
    @SerializedName("pagination")
    val pagination: PaginationResponse
)
