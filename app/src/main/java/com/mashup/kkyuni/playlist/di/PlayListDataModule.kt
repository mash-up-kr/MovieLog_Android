package com.mashup.kkyuni.playlist.di

import com.mashup.kkyuni.playlist.domain.repository.PlayListRepository
import com.mashup.kkyuni.playlist.data.repository.PlayListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlayListDataModule {
    @Binds
    abstract fun bindPlayListRepository(
        repositoryImpl: PlayListRepositoryImpl
    ): PlayListRepository
}