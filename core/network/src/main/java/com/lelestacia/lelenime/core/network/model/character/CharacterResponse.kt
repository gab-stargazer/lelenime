package com.lelestacia.lelenime.core.network.model.character

import com.google.gson.annotations.SerializedName
import com.lelestacia.lelenime.core.network.model.voice_actor.VoiceActorResponse

data class CharacterResponse(
    @SerializedName("character")
    val characterData: CharacterResponseData,

    @SerializedName("role")
    val role: String,

    @SerializedName("favorites")
    val favorites: Int = 0,

    @SerializedName("voice_actors")
    val voiceActors: List<VoiceActorResponse>
) {
    data class CharacterResponseData(
        @SerializedName("mal_id")
        val malID: Int,

        @SerializedName("images")
        val images: CharacterImageResponse,

        @SerializedName("name")
        val name: String
    ) {
        data class CharacterImageResponse(
            @SerializedName("webp")
            val webp: Webp
        ) {
            data class Webp(
                @SerializedName("image_url")
                val imageUrl: String
            )
        }
    }
}
