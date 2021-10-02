package com.mashup.kkyuni.music.data.di

import com.mashup.kkyuni.feature.music.data.VideoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideVideoService(@Named("youtube_api") retrofit: Retrofit) =
        retrofit.create(VideoService::class.java)
}