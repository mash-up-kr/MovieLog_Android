package com.mashup.kkyuni.feature.music.data.di

import com.mashup.kkyuni.feature.music.data.MusicRepositoryImpl
import com.mashup.kkyuni.feature.music.domain.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsMusicRepository(repositoryImpl: MusicRepositoryImpl): MusicRepository

}