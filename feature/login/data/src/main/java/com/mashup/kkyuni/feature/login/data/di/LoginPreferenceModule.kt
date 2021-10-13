package com.mashup.kkyuni.feature.login.data.di

import com.mashup.kkyuni.core.preference.SharedPreferenceManager
import com.mashup.kkyuni.feature.login.data.source.LoginPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginPreferenceModule {

    @Provides
    @Singleton
    fun provideLoginPreferenceManager(sharedPreferenceManager: SharedPreferenceManager): LoginPreferenceManager =
        LoginPreferenceManager(sharedPreferenceManager)
}