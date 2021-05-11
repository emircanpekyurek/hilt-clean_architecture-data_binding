package com.pekyurek.emircan.presentation.core.data.domain.usecase.detail

import com.pekyurek.emircan.presentation.core.data.domain.usecase.UseCase
import com.pekyurek.emircan.presentation.core.data.model.episode.Episode
import com.pekyurek.emircan.presentation.core.data.model.episode.EpisodeDetailRequest
import com.pekyurek.emircan.presentation.core.data.repository.RAMRepository
import com.pekyurek.emircan.presentation.core.data.repository.ResultStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeDetailUseCase @Inject constructor(private val repository: RAMRepository) :
    UseCase<EpisodeDetailRequest, Episode> {

    override suspend fun execute(request: EpisodeDetailRequest): Flow<ResultStatus<Episode>> =
        repository.getEpisodeDetail(request.episodeId)

}