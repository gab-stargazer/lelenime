package com.lelestacia.lelenime.core.network.source.character

import com.lelestacia.lelenime.core.network.endpoint.CharacterAPI
import com.lelestacia.lelenime.core.network.model.character.CharacterDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CharacterNetworkService @Inject constructor(
    private val characterAPI: CharacterAPI,
    private val ioDispatcher: CoroutineContext = Dispatchers.IO
) : ICharacterNetworkService {

    override suspend fun getCharacterDetailByCharacterID(characterID: Int): CharacterDetailResponse {
        return withContext(ioDispatcher) {
            characterAPI.getCharacterDetailByCharacterID(id = characterID).data
        }
    }
}