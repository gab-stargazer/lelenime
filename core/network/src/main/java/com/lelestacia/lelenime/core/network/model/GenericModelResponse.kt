package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a generic model response from an API, including a unique ID, title, and images.
 *
 * @property malID The unique ID of the model.
 * @property images The images associated with the model.
 * @property title The title of the model.
 */
data class GenericModelResponse(
    @SerializedName("mal_id")
    val malID: Int,

    @SerializedName("images")
    val images: GenericImagesResponse,

    @SerializedName("title")
    val title: String
) {

    /**
     * Represents the response data for images in the generic format.
     *
     * @property webp The images in the WebP format.
     */
    data class GenericImagesResponse(
        @SerializedName("webp")
        val webp: Webp
    ) {

        /**
         * Represents the response data for images in the WebP format.
         *
         * @property largeImageURL The URL for the large image.
         */
        data class Webp(
            @SerializedName("large_image_url")
            val largeImageURL: String
        )
    }
}
