package com.lelestacia.lelenime.core.database.animeStuff.entity.anime

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lelestacia.lelenime.core.database.animeStuff.util.DatabaseConstant.ANIME_ID
import com.lelestacia.lelenime.core.database.animeStuff.util.DatabaseConstant.CREATED_AT
import com.lelestacia.lelenime.core.database.animeStuff.util.DatabaseConstant.UPDATED_AT
import java.util.Date

@Entity(tableName = "anime_table")
data class AnimeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ANIME_ID)
    val animeID: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @Embedded
    val trailer: Trailer?,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "title_english")
    val titleEnglish: String?,

    @ColumnInfo(name = "title_japanese")
    val titleJapanese: String?,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "source")
    val source: String,

    @ColumnInfo(name = "episodes")
    val episodes: Int?,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "airing")
    val airing: Boolean,

    @ColumnInfo(name = "started_date")
    val startedDate: String?,

    @ColumnInfo(name = "finished_date")
    val finishedDate: String?,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "rating")
    val rating: String,

    @ColumnInfo(name = "score")
    val score: Double?,

    @ColumnInfo(name = "scored_by")
    val scoredBy: Int?,

    @ColumnInfo(name = "rank")
    val rank: Int,

    @ColumnInfo(name = "synopsis")
    val synopsis: String?,

    @ColumnInfo(name = "season")
    val season: String?,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "studios")
    val studios: List<String>,

    @ColumnInfo(name = "genres")
    val genres: List<String>,

    @ColumnInfo(name = "favorite")
    val isFavorite: Boolean,

    @ColumnInfo(name = "last_viewed")
    val lastViewed: Date,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: Date,

    @ColumnInfo(name = UPDATED_AT)
    val updatedAt: Date?
) {

    data class Trailer(
        @ColumnInfo(name = "trailer_id")
        val id: String?,

        @ColumnInfo(name = "trailer_url")
        val url: String?,

        @ColumnInfo(name = "trailer_image")
        val image: String?
    )
}
