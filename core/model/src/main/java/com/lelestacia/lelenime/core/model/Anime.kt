package com.lelestacia.lelenime.core.model

data class Anime(
    val malID: Int,
    val coverImages: String,
    val trailer: Trailer?,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val type: String,
    val source: String,
    val episodes: Int?,
    val status: String,
    val airing: Boolean,
    val startedDate: String?,
    val finishedDate: String?,
    val duration: String,
    val rating: String,
    val score: Double?,
    val scoredBy: Int?,
    val rank: Int,
    val synopsis: String?,
    val season: String?,
    val year: Int,
    val studios: List<String>,
    val genres: List<String>,
    val isFavorite: Boolean
) {
    data class Trailer(
        val youtubeId: String?,
        val url: String?,
        val images: String?
    )
}
