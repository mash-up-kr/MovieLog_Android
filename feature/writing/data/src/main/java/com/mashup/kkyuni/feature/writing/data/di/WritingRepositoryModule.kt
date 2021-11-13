package com.mashup.kkyuni.feature.writing.data.di

import com.mashup.kkyuni.feature.writing.data.repository.WritingRepository
import com.mashup.kkyuni.feature.writing.data.repository.impl.WritingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class WritingRepositoryModule {

    @Binds
    abstract fun bindWritingRepository(
        impl: WritingRepositoryImpl
    ): WritingRepository
}