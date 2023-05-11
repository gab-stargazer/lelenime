package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing an error response from a network call.
 *
 * @property status The HTTP status code of the error.
 * @property type The type of the error.
 * @property message The error message.
 * @property error The specific error string.
 */
data class ErrorResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: String
)
