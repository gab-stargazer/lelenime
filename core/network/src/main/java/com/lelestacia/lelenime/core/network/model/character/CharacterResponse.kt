package com.lelestacia.lelenime.core.network.model.character

import com.google.gson.annotations.SerializedName
import com.lelestacia.lelenime.core.network.model.voiceActor.VoiceActorResponse

/**
 * Data class representing a response containing information about a character.
 *
 * @property characterData An instance of [CharacterResponseData] containing information about the character.
 * @property role The role the character played in a particular anime or manga.
 * @property favorites The number of times the character has been added to a user's favorites.
 * @property voiceActors A list of [VoiceActorResponse] instances containing information about voice actors who have played this character.
 */
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

    /**
     * Data class representing information about a character.
     *
     * @property malID The ID of the character on MyAnimeList.
     * @property images An instance of [CharacterImageResponse] containing image URLs for the character.
     * @property name The name of the character.
     */
    data class CharacterResponseData(
        @SerializedName("mal_id")
        val malID: Int,

        @SerializedName("images")
        val images: CharacterImageResponse,

        @SerializedName("name")
        val name: String
    ) {

        /**
         * Data class representing image URLs for a character.
         *
         * @property webp An instance of [Webp] containing a webp image URL for the character.
         */
        data class CharacterImageResponse(
            @SerializedName("webp")
            val webp: Webp
        ) {

            /**
             * Data class representing a webp image URL.
             *
             * @property imageUrl The URL for the webp image.
             */
            data class Webp(
                @SerializedName("image_url")
                val imageUrl: String
            )
        }
    }
}
