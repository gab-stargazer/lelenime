package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * Response model for reviews.
 *
 * @property malID The ID of the review on MyAnimeList.
 * @property type The type of the reviewed item (e.g., anime, manga).
 * @property reactions The reactions given to the review.
 * @property date The date the review was written.
 * @property review The written review.
 * @property score The score given to the reviewed item.
 * @property tags The tags associated with the review.
 * @property isSpoiler Whether the review contains spoilers.
 * @property isPreliminary Whether the review is a preliminary review.
 * @property user Information about the user who wrote the review.
 */
data class GenericReviewResponse(
    @SerializedName("mal_id")
    val malID: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("reactions")
    val reactions: ReviewReactionResponse,

    @SerializedName("date")
    val date: String,

    @SerializedName("review")
    val review: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("tags")
    val tags: List<String>,

    @SerializedName("is_spoiler")
    val isSpoiler: Boolean,

    @SerializedName("is_preliminary")
    val isPreliminary: Boolean,

    @SerializedName("user")
    val user: UserReviewResponse
) {

    /**
     * Information about the user who wrote the review.
     *
     * @property userName The user's name.
     * @property images The user's images.
     */
    data class UserReviewResponse(
        @SerializedName("username")
        val userName: String,

        @SerializedName("images")
        val images: Images
    ) {

        /**
         * The user's images.
         *
         * @property webp The user's webp image.
         */
        data class Images(
            @SerializedName("webp")
            val webp: Webp
        ) {

            /**
             * The user's webp image.
             *
             * @property imageURL The URL of the user's webp image.
             */
            data class Webp(
                @SerializedName("image_url")
                val imageURL: String
            )
        }
    }

    /**
     * Reactions given to the review.
     *
     * @property overall The overall reaction to the review.
     * @property niceReaction The number of "nice" reactions.
     * @property loveReaction The number of "love it" reactions.
     * @property funnyReaction The number of "funny" reactions.
     * @property confusingReaction The number of "confusing" reactions.
     * @property informativeReaction The number of "informative" reactions.
     * @property wellWrittenReaction The number of "well written" reactions.
     * @property creativeReaction The number of "creative" reactions.
     */
    data class ReviewReactionResponse(
        @SerializedName("overall")
        val overall: Int,

        @SerializedName("nice")
        val niceReaction: Int,

        @SerializedName("love_it")
        val loveReaction: Int,

        @SerializedName("funny")
        val funnyReaction: Int,

        @SerializedName("confusing")
        val confusingReaction: Int,

        @SerializedName("informative")
        val informativeReaction: Int,

        @SerializedName("well_written")
        val wellWrittenReaction: Int,

        @SerializedName("creative")
        val creativeReaction: Int
    )
}
