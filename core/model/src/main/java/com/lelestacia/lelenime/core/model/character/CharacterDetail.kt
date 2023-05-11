package com.lelestacia.lelenime.core.model.character

data class CharacterDetail(
    val characterID: Int,
    val name: String,
    val characterKanjiName: String,
    val characterNickNames: List<String>,
    val images: String,
    val role: String,
    val favoriteBy: Int,
    val characterInformation: String
)
