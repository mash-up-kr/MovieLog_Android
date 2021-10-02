package com.mashup.kkyuni.playlist.di

import com.mashup.kkyuni.playlist.presenter.PlayListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class PlayListPresentationModule {

    @Provides
    fun providePlayListAdapter(): PlayListAdapter = PlayListAdapter()
}