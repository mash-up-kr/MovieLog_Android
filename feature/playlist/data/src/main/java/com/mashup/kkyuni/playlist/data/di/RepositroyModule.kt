package com.mashup.kkyuni.playlist.data.di

import com.mashup.kkyuni.playlist.data.repository.PlayListRepository
import com.mashup.kkyuni.playlist.data.repository.impl.PlayListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositroyModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPlayListRepository(
        repositoryImpl: PlayListRepositoryImpl
    ): PlayListRepository
}