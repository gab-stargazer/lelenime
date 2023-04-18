package com.lelestacia.lelenime.core.network.model

import com.google.gson.annotations.SerializedName

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
    data class UserReviewResponse(

        @SerializedName("username")
        val userName: String,

        @SerializedName("images")
        val images: Images
    ) {

        data class Images(

            @SerializedName("webp")
            val webp: Webp
        ) {

            data class Webp(

                @SerializedName("image_url")
                val imageURL: String
            )
        }
    }

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
