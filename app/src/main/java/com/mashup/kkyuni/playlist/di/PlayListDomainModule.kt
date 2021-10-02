package com.mashup.kkyuni.playlist.di

import com.mashup.kkyuni.playlist.data.repository.PlayListRepositoryImpl
import com.mashup.kkyuni.playlist.domain.repository.PlayListRepository
import com.mashup.kkyuni.playlist.domain.usecase.GetPlayListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PlayListDomainModule {
    @Binds
    abstract fun bindPlayListRepository(
        repositoryImpl: PlayListRepositoryImpl
    ): PlayListRepository

    companion object {
        @Provides
        fun provideGetPlayListUseCase(
            repository: PlayListRepository
        ): GetPlayListUseCase = GetPlayListUseCase(repository)
    }
}