package com.pekyurek.emircan.presentation.core.data.repository

import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterResponse
import com.pekyurek.emircan.presentation.core.data.model.episode.Episode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RAMApiService {

    @GET("/api/character")
    suspend fun getCharacter(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null
    ): Response<CharacterResponse>

    @GET("/api/character/{characterId}")
    suspend fun getCharacterDetail(@Path("characterId") characterId: Int): Response<Character>

    @GET("/api/episode/{episodeId}")
    suspend fun getEpisodeDetail(@Path("episodeId") episodeId: String): Response<Episode>

}