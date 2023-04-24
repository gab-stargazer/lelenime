package com.lelestacia.lelenime.core.network.model.character

import com.google.gson.annotations.SerializedName
import com.lelestacia.lelenime.core.network.model.voice_actor.VoiceActorResponse

/**
 * Represents detailed information about a character.
 *
 * @property characterMalID The MyAnimeList ID of the character.
 * @property characterName The name of the character.
 * @property characterKanjiName The kanji name of the character, if available.
 * @property characterNickNames The list of nicknames associated with the character.
 * @property characterFavoriteCount The number of users who have favorited the character.
 * @property characterInformation The detailed information about the character.
 * @property voiceActors The list of voice actors who have portrayed this character.
 */
data class CharacterDetailResponse(
    @SerializedName("mal_id")
    val characterMalID: Int,

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
