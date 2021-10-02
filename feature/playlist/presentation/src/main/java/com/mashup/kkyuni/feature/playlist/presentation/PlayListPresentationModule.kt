package com.mashup.kkyuni.feature.playlist.presentation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class PlayListPresentationModule {

    @Provides
    fun providePlayListAdapter(): PlayListAdapter =
        PlayListAdapter()
}