package com.lelestacia.lelenime.core.network.model.anime

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("mal_id")
    val malId: Int,

    @SerializedName("images")
    val coverImages: AnimeImagesDTO,

    @SerializedName("trailer")
    val trailer: AnimeTrailerResponse?,

    @SerializedName("title")
    val title: String,

    @SerializedName("title_english")
    val titleEnglish: String?,

    @SerializedName("title_japanese")
    val titleJapanese: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("source")
    val source: String,

    @SerializedName("episodes")
    val episodes: Int?,

    @SerializedName("status")
    val status: String,

    @SerializedName("airing")
    val airing: Boolean,

    @SerializedName("aired")
    val aired: AnimeAiringInformationResponse,

    @SerializedName("duration")
    val duration: String,

    @SerializedName("rating")
    val rating: String?,

    @SerializedName("score")
    val score: Double?,

    @SerializedName("scored_by")
    val scoredBy: Int?,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("synopsis")
    val synopsis: String?,

    @SerializedName("season")
    val season: String?,

    @SerializedName("year")
    val year: Int,

    @SerializedName("studios")
    val studio: List<AnimeStudioResponse>,

    @SerializedName("genres")
    val genres: List<AnimeGenresResponse>
) {
    data class AnimeImagesDTO(
        @SerializedName("webp")
        val webp: Webp
    ) {
        data class Webp(
            @SerializedName("image_url")
            val imageUrl: String,

            @SerializedName("large_image_url")
            val largeImageUrl: String
        )
    }

    data class AnimeTrailerResponse(
        @SerializedName("youtube_id")
        val youtubeId: String?,

        @SerializedName("url")
        val url: String?,

        @SerializedName("embed_url")
        val embedUrl: String?,

        @SerializedName("images")
        val images: AnimeTrailerImagesResponse?
    ) {
        data class AnimeTrailerImagesResponse(
            @SerializedName("medium_image_url")
            val mediumImageUrl: String,

            @SerializedName("large_image_url")
            val largeImageUrl: String,

            @SerializedName("maximum_image_url")
            val maximumImageUrl: String
        )
    }

    data class AnimeGenresResponse(
        @SerializedName("mal_id")
        val malId: Int,

        @SerializedName("type")
        val type: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("url")
        val url: String
    )

    data class AnimeAiringInformationResponse(
        @SerializedName("from")
        val from: String?,

        @SerializedName("to")
        val to: String?
    )

    data class AnimeStudioResponse(
        @SerializedName("mal_id")
        val malID: Int,

        @SerializedName("type")
        val type: String,

        @SerializedName("name")
        val name: String
    )
}
