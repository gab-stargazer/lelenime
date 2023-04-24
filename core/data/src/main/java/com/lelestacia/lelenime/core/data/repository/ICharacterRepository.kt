package com.lelestacia.lelenime.core.data.repository

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.character.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {
    fun getAnimeCharactersByAnimeID(animeID: Int): Flow<Resource<List<Character>>>
}