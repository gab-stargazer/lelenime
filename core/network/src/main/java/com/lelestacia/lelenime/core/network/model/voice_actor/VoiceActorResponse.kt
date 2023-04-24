package com.lelestacia.lelenime.core.network.model.voice_actor

import com.google.gson.annotations.SerializedName

data class VoiceActorResponse(
    @SerializedName("person")
    val person: PersonResponse,

    @SerializedName("language")
    val language: String
) {
    data class PersonResponse(
        @SerializedName("mal_id")
        val malID: Int,

        @SerializedName("images")
        val image: VoiceActorImageResponse,

        @SerializedName("name")
        val name: String,
    ) {
        data class VoiceActorImageResponse(
            @SerializedName("jpg")
            val jpg: Jpeg
        ) {
            data class Jpeg(
                @SerializedName("image_url")
                val imageUrl: String
            )
        }
    }
}
