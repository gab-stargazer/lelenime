package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

data class GenericResponse<T>(
    @SerializedName("data")
    val data: T
)
