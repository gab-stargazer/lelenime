package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

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
