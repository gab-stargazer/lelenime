package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a generic recommendation response.
 *
 * @property malID The ID of the recommendation.
 * @property entry The list of recommended entries.
 * @property content The recommendation content.
 * @property date The recommendation date.
 * @property user The user who made the recommendation.
 */
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

    /**
     * Represents a user recommendation for an anime.
     *
     * @property userName The username of the user who made the recommendation.
     */
    data class UserRecommendationAnimeDTO(
        @SerializedName("username")
        val userName: String
    )
}
