package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing pagination information in a response.
 *
 * @property lastVisiblePage The number of the last visible page.
 * @property hasNextPage A boolean value indicating whether there is a next page or not.
 */
data class PaginationResponse(
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean
)
