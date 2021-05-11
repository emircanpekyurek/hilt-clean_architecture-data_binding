package com.pekyurek.emircan.presentation.core.di

import com.pekyurek.emircan.presentation.core.data.domain.usecase.character.CharacterUseCase
import com.pekyurek.emircan.presentation.core.data.domain.usecase.detail.CharacterDetailUseCase
import com.pekyurek.emircan.presentation.core.data.domain.usecase.detail.EpisodeDetailUseCase
import com.pekyurek.emircan.presentation.core.data.repository.RAMRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    fun provideCharacterUseCase(repository: RAMRepository) = CharacterUseCase(repository)

    @Provides
    fun provideCharacterDetailUseCase(repository: RAMRepository) = CharacterDetailUseCase(repository)

    @Provides
    fun provideEpisodeDetailUseCase(repository: RAMRepository) = EpisodeDetailUseCase(repository)
}