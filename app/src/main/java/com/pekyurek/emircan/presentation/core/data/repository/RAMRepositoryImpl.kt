package com.pekyurek.emircan.presentation.core.data.repository

import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterResponse
import com.pekyurek.emircan.presentation.core.data.model.episode.Episode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RAMRepositoryImpl @Inject constructor(private val remoteDataSource: RAMRemoteDataSource) :
    RAMRepository {

    override suspend fun getCharacter(
        page: Int,
        name: String?,
        status: String?
    ): Flow<ResultStatus<CharacterResponse>> =
        remoteDataSource.getCharacter(page, name, status)

    override suspend fun getCharacterDetail(characterId: Int): Flow<ResultStatus<Character>> =
        remoteDataSource.getCharacterDetail(characterId)

    override suspend fun getEpisodeDetail(episodeId: String): Flow<ResultStatus<Episode>> =
        remoteDataSource.getEpisodeDetail(episodeId)
}