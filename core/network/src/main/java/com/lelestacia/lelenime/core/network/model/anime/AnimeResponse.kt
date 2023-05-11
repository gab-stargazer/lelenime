package com.lelestacia.lelenime.core.network.model.anime

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a response object for Anime API.
 *
 * @property malId The MyAnimeList ID of the anime.
 * @property coverImages The cover images of the anime.
 * @property trailer The trailer of the anime.
 * @property title The title of the anime.
 * @property titleEnglish The English title of the anime.
 * @property titleJapanese The Japanese title of the anime.
 * @property type The type of the anime.
 * @property source The source of the anime.
 * @property episodes The total number of episodes of the anime.
 * @property status The airing status of the anime.
 * @property airing Whether the anime is currently airing or not.
 * @property aired The airing information of the anime.
 * @property duration The duration of each episode of the anime.
 * @property rating The rating of the anime.
 * @property score The score of the anime on MyAnimeList.
 * @property scoredBy The number of users who have scored the anime on MyAnimeList.
 * @property rank The ranking of the anime on MyAnimeList.
 * @property synopsis The synopsis of the anime.
 * @property season The season the anime aired in.
 * @property year The year the anime aired in.
 * @property studio The studios that produced the anime.
 * @prop genres The genres of the anime.
 */
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

    /**
     * Data class representing cover images of the anime.
     *
     * @property webp The cover image in WebP format.
     */
    data class AnimeImagesDTO(
        @SerializedName("webp")
        val webp: Webp
    ) {

        /**
         * Data class representing the cover image in WebP format.
         *
         * @property imageUrl The URL of the cover image.
         * @property largeImageUrl The URL of the large version of the cover image.
         */
        data class Webp(
            @SerializedName("image_url")
            val imageUrl: String,

            @SerializedName("large_image_url")
            val largeImageUrl: String
        )
    }

    /**
     * Represents a trailer for an anime.
     *
     * @property youtubeID The ID of the trailer on YouTube.
     * @property url The URL of the trailer.
     * @property embedUrl The URL used to embed the trailer.
     * @property images The images associated with the trailer.
     */
    data class AnimeTrailerResponse(
        @SerializedName("youtube_id")
        val youtubeID: String?,

        @SerializedName("url")
        val url: String?,

        @SerializedName("embed_url")
        val embedUrl: String?,

        @SerializedName("images")
        val images: AnimeTrailerImagesResponse?
    ) {

        /**
         * Data class representing trailer images for an anime.
         *
         * @property mediumImageUrl the URL for the medium-sized image.
         * @property largeImageUrl the URL for the large-sized image.
         * @property maximumImageUrl the URL for the maximum-sized image.
         */
        data class AnimeTrailerImagesResponse(
            @SerializedName("medium_image_url")
            val mediumImageUrl: String,

            @SerializedName("large_image_url")
            val largeImageUrl: String,

            @SerializedName("maximum_image_url")
            val maximumImageUrl: String
        )
    }

    /**
     * Represents an anime genre response from the MyAnimeList API.
     *
     * @property malID The MyAnimeList ID of the anime genre.
     * @property type The type of the anime genre.
     * @property name The name of the anime genre.
     * @property url The URL of the anime genre's page on MyAnimeList.
     */
    data class AnimeGenresResponse(
        @SerializedName("mal_id")
        val malID: Int,

        @SerializedName("type")
        val type: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("url")
        val url: String
    )

    /**
     * Represents the airing information for an anime.
     *
     * @property from The date and time when the anime began airing, in ISO 8601 format.
     * @property to The date and time when the anime stopped airing, in ISO 8601 format.
     */
    data class AnimeAiringInformationResponse(
        @SerializedName("from")
        val from: String?,

        @SerializedName("to")
        val to: String?
    )

    /**
     * Data class representing a studio that worked on an anime.
     *
     * @property malID the MyAnimeList ID of the studio.
     * @property type the type of studio (e.g. "anime", "movie", "music").
     * @property name the name of the studio.
     */
    data class AnimeStudioResponse(
        @SerializedName("mal_id")
        val malID: Int,

        @SerializedName("type")
        val type: String,

        @SerializedName("name")
        val name: String
    )
}
