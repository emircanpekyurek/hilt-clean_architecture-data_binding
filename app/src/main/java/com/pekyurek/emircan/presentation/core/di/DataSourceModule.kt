package com.pekyurek.emircan.presentation.core.di

import android.content.Context
import com.pekyurek.emircan.presentation.core.data.repository.RAMApiService
import com.pekyurek.emircan.presentation.core.data.repository.RAMRemoteDataSource
import com.pekyurek.emircan.presentation.core.data.repository.RAMRepository
import com.pekyurek.emircan.presentation.core.data.repository.RAMRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideRAMRemoteDataSource(
        @ApplicationContext context: Context,
        ramApiService: RAMApiService
    ) =
        RAMRemoteDataSource(context, ramApiService)

    @Provides
    fun provideRAMRepositoryImpl(ramRemoteDataSource: RAMRemoteDataSource): RAMRepository =
        RAMRepositoryImpl(ramRemoteDataSource)
}