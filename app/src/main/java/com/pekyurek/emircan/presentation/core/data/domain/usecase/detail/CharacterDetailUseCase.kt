package com.pekyurek.emircan.presentation.core.data.domain.usecase.detail

import com.pekyurek.emircan.presentation.core.data.domain.usecase.UseCase
import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterDetailRequest
import com.pekyurek.emircan.presentation.core.data.repository.RAMRepository
import com.pekyurek.emircan.presentation.core.data.repository.ResultStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(private val repository: RAMRepository)
    : UseCase<CharacterDetailRequest, Character> {

    override suspend fun execute(request: CharacterDetailRequest): Flow<ResultStatus<Character>> =
        repository.getCharacterDetail(request.characterId)
}