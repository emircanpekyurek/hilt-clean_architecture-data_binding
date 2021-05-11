package com.pekyurek.emircan.presentation.core.data.domain.usecase.character

import androidx.paging.*
import com.pekyurek.emircan.presentation.core.data.domain.usecase.UseCase
import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterRequest
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterResponse
import com.pekyurek.emircan.presentation.core.data.repository.RAMRepository
import com.pekyurek.emircan.presentation.core.data.repository.ResultStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val repository: RAMRepository)

    : UseCase<CharacterRequest, CharacterResponse> {

    fun pagingFlow(characterRequest: CharacterRequest): Flow<PagingData<Character>> =
        Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource(this, characterRequest) }
        ).flow

    override suspend fun execute(request: CharacterRequest): Flow<ResultStatus<CharacterResponse>> =
        repository.getCharacter(request.pageNumber, request.name, request.status)

}