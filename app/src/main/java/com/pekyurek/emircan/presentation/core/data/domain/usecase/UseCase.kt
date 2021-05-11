package com.pekyurek.emircan.presentation.core.data.domain.usecase

import com.pekyurek.emircan.presentation.core.data.repository.ResultStatus
import kotlinx.coroutines.flow.Flow

interface UseCase<Req, Res> {

    suspend fun execute(request: Req): Flow<ResultStatus<Res>>

}