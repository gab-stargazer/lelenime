package com.lelestacia.lelenime.core.network.model.character

import com.google.gson.annotations.SerializedName
import com.lelestacia.lelenime.core.network.model.voice_actor.VoiceActorResponse

data class CharacterDetailResponse(
    @SerializedName("mal_id")
    val characterMalId: Int,

    @SerializedName("name")
    val characterName: String,

    @SerializedName("name_kanji")
    val characterKanjiName: String?,

    @SerializedName("nicknames")
    val characterNickNames: List<String> = emptyList(),

    @SerializedName("favorites")
    val characterFavoriteCount: Int,

    @SerializedName("about")
    val characterInformation: String?,

    @SerializedName("voices")
    val voiceActors: List<VoiceActorResponse>
)
