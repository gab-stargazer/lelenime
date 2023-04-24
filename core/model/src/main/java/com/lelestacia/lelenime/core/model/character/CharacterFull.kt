package com.lelestacia.lelenime.core.model.character

import com.lelestacia.lelenime.core.model.VoiceActor

data class CharacterFull(
    val characterID: Int,
    val name: String,
    val characterKanjiName: String,
    val characterNickNames: List<String>,
    val images: String,
    val role: String,
    val favoriteBy: Int,
    val characterInformation: String,
    val voiceActors: List<VoiceActor>
)