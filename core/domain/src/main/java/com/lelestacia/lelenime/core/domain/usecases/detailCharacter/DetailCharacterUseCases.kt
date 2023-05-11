package com.lelestacia.lelenime.core.domain.usecases.detailCharacter

import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.data.repository.ICharacterRepository
import com.lelestacia.lelenime.core.model.character.CharacterDetail
import kotlinx.coroutines.flow.Flow

class DetailCharacterUseCases(
    private val characterRepository: ICharacterRepository
) : IDetailCharacterUseCases {
    override fun getCharacterInformationByCharacterId(characterID: Int): Flow<Resource<CharacterDetail>> {
        return characterRepository.getCharacterDetailByID(characterID)
    }
}
