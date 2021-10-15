package com.mashup.kkyuni.playlist.data.di

import com.mashup.kkyuni.playlist.data.service.PlayListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideVideoService(@Named("kkyuni_api") retrofit: Retrofit): PlayListService =
        retrofit.create(PlayListService::class.java)
}