package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

data class GenericRecommendationResponse(

    @SerializedName("mal_id")
    val malID: Int,

    @SerializedName("entry")
    val entry: List<GenericModelResponse>,

    @SerializedName("content")
    val content: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("user")
    val user: UserRecommendationAnimeDTO
) {

    data class UserRecommendationAnimeDTO(

        @SerializedName("username")
        val userName: String
    )
}
