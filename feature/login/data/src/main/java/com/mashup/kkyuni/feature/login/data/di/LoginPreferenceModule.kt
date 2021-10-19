package com.mashup.kkyuni.feature.login.data.di

import com.mashup.kkyuni.feature.login.data.source.LoginPreferenceManagerImpl
import com.mashup.kkyuni.feature.login.domain.source.LoginPreferenceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LoginPreferenceModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface AbstractModule {
        @Binds
        fun bindsLoginPreferenceManager(manager: LoginPreferenceManagerImpl): LoginPreferenceManager
    }
}