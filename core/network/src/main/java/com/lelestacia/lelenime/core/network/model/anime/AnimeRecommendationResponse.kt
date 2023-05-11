package com.lelestacia.lelenime.core.network.model.anime

import com.google.gson.annotations.SerializedName
import com.lelestacia.lelenime.core.network.model.GenericModelResponse

/**
 * Data class representing an anime recommendation response.
 *
 * @property entry The anime being recommended.
 * @property votes The number of votes the recommendation has received.
 */
data class AnimeRecommendationResponse(

    @SerializedName("entry")
    val entry: GenericModelResponse,

    @SerializedName("votes")
    val votes: Int
)
