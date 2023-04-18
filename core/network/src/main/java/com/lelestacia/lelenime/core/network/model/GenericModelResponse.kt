package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

data class GenericModelResponse(

    @SerializedName("mal_id")
    val malID: Int,

    @SerializedName("images")
    val images: GenericImagesResponse,

    @SerializedName("title")
    val title: String
) {
    data class GenericImagesResponse(
        @SerializedName("webp")
        val webp: Webp
    ) {
        data class Webp(
            @SerializedName("large_image_url")
            val largeImageURL: String
        )
    }
}
