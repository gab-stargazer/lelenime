package com.lelestacia.lelenime.core.domain.usecases.detailCharacter

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.model.character.CharacterDetail
import kotlinx.coroutines.flow.Flow

interface IDetailCharacterUseCases {
    fun getCharacterInformationByCharacterId(characterID: Int): Flow<Resource<CharacterDetail>>
}