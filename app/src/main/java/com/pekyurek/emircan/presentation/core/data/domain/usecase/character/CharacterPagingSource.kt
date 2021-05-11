package com.pekyurek.emircan.presentation.core.data.domain.usecase.character

import android.content.res.Resources
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pekyurek.emircan.presentation.core.data.domain.exception.service.FailResponseException
import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterRequest
import com.pekyurek.emircan.presentation.core.data.repository.ResultStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect


class CharacterPagingSource(
    private val characterUseCase: CharacterUseCase,
    private val characterRequest: CharacterRequest
) :
    PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: FIRST_PAGE
        var result: LoadResult<Int, Character> = LoadResult.Error(Resources.NotFoundException())

        characterRequest.pageNumber = position
        characterUseCase.execute(characterRequest).collect {
            when (it) {
                is ResultStatus.Success ->
                    result = it.data.info?.pages?.let { totalPageCount ->
                        LoadResult.Page(
                            data = it.data.characters.orEmpty(),
                            nextKey = if (position < totalPageCount) position + 1 else null,
                            prevKey = null
                        )
                    } ?: LoadResult.Error(NullPointerException())
                is ResultStatus.Exception -> result =
                    if (it.exception is FailResponseException && it.exception.responseStatus == 404) {
                        LoadResult.Page(data = emptyList(), null, null)
                    } else {
                        LoadResult.Error(it.exception)
                    }
            }
        }
        return result
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    companion object {
        const val FIRST_PAGE = 1
    }
}