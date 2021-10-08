package com.mashup.kkyuni.playlist.data

import com.mashup.kkyuni.playlist.data.repository.PlayListRepository
import com.mashup.kkyuni.playlist.data.repository.impl.PlayListRepositoryImpl
import com.mashup.kkyuni.playlist.data.service.PlayListService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlayListDataModule {
    @Provides
    @Singleton
    fun provideVideoService(@Named("kkyuni_api") retrofit: Retrofit) =
        retrofit.create(PlayListService::class.java)

    @Module
    @InstallIn(ViewModelComponent::class)
    interface AbstractModule {
        @Binds
        @ViewModelScoped
        fun bindPlayListRepository(
            repositoryImpl: PlayListRepositoryImpl
        ): PlayListRepository
    }
}