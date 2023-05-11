package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * A generic response class that contains a single data object.
 *
 * @param T The type of data object in the response.
 * @property data The data object in the response.
 */
data class GenericResponse<T>(
    @SerializedName("data")
    val data: T
)
