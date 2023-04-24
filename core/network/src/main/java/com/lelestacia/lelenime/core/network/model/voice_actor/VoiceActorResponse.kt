package com.lelestacia.lelenime.core.network.model.voice_actor

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a voice actor response in the MyAnimeList API.
 *
 * @property person the information about the person who voiced the character.
 * @property language the language in which the character was voiced.
 */
data class VoiceActorResponse(
    @SerializedName("person")
    val person: PersonResponse,

    @SerializedName("language")
    val language: String
) {

    /**
     * Data class representing the information about a voice actor's person.
     *
     * @property malID the MyAnimeList ID of the person.
     * @property image the URLs for the person's image.
     * @property name the name of the person.
     */
    data class PersonResponse(
        @SerializedName("mal_id")
        val malID: Int,

        @SerializedName("images")
        val image: VoiceActorImageResponse,

        @SerializedName("name")
        val name: String,
    ) {

        /**
         * Data class representing the URLs for a voice actor's image.
         *
         * @property jpg the URL for the JPEG format of the image.
         */
        data class VoiceActorImageResponse(
            @SerializedName("jpg")
            val jpg: Jpeg
        ) {

            /**
             * Data class representing the URL for the JPEG format of a voice actor's image.
             *
             * @property imageUrl the URL for the JPEG format of the image.
             */
            data class Jpeg(
                @SerializedName("image_url")
                val imageUrl: String
            )
        }
    }
}
