package com.pekyurek.emircan.presentation.core.data.repository

import android.content.Context
import com.pekyurek.emircan.R
import com.pekyurek.emircan.presentation.core.data.domain.exception.ServiceException
import com.pekyurek.emircan.presentation.core.data.domain.exception.service.FailResponseException
import com.pekyurek.emircan.presentation.core.data.domain.exception.service.NullResponseException
import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterResponse
import com.pekyurek.emircan.presentation.core.data.model.episode.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class RAMRemoteDataSource @Inject constructor(
    private val context: Context,
    private val apiService: RAMApiService
) :
    RAMRepository {

    override suspend fun getCharacter(
        page: Int,
        name: String?,
        status: String?
    ): Flow<ResultStatus<CharacterResponse>> = execute { apiService.getCharacter(page, name, status) }

    override suspend fun getCharacterDetail(characterId: Int): Flow<ResultStatus<Character>> =
        execute { apiService.getCharacterDetail(characterId) }

    override suspend fun getEpisodeDetail(episodeId: String): Flow<ResultStatus<Episode>> =
        execute { apiService.getEpisodeDetail(episodeId) }

    private suspend fun <T> execute(suspendResponse: suspend () -> Response<T>): Flow<ResultStatus<T>> =
        flow<ResultStatus<T>> {
            val response = suspendResponse.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ResultStatus.Success(it))
                } ?: emit(ResultStatus.Exception(NullResponseException(context)))
            } else {
                emit(
                    ResultStatus.Exception(
                        FailResponseException(
                            context,
                            response.code()
                        )
                    )
                )
            }
        }.onStart {
            emit(ResultStatus.Loading(true))
        }.catch { e ->
            emit(
                ResultStatus.Exception(
                    ServiceException(
                        e.message ?: e.localizedMessage ?: context.getString(
                            R.string.exception_service_generic_error_message
                        )
                    )
                )
            )
        }.onCompletion {
            emit(ResultStatus.Loading(false))
        }.flowOn(Dispatchers.IO)

}