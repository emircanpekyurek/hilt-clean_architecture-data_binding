package com.pekyurek.emircan.presentation.core.data.repository

import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterResponse
import com.pekyurek.emircan.presentation.core.data.model.episode.Episode
import kotlinx.coroutines.flow.Flow

interface RAMRepository {

    suspend fun getCharacter(
        page: Int,
        name: String?,
        status: String?
    ): Flow<ResultStatus<CharacterResponse>>

    suspend fun getCharacterDetail(
        characterId: Int
    ): Flow<ResultStatus<Character>>

    suspend fun getEpisodeDetail(
        episodeId: String
    ): Flow<ResultStatus<Episode>>

}