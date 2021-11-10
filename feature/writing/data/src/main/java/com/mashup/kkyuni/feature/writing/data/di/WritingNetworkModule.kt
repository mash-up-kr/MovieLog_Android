package com.mashup.kkyuni.feature.writing.data.di

import com.mashup.kkyuni.feature.writing.data.service.WritingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WritingNetworkModule {

    @Provides
    @Singleton
    fun provideWritingService(@Named("kkyuni_api") retrofit: Retrofit) =
        retrofit.create(WritingService::class.java)
}