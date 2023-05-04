package com.lelestacia.lelenime.core.network.source.character

import com.lelestacia.lelenime.core.network.model.character.CharacterDetailResponse

interface ICharacterNetworkService {
    suspend fun getCharacterDetailByCharacterID(characterID: Int): CharacterDetailResponse
}