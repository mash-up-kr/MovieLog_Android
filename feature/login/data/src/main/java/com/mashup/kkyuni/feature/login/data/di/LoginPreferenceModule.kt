package com.mashup.kkyuni.feature.login.data.di

import com.mashup.kkyuni.feature.login.data.source.LoginPreferenceManagerImpl
import com.mashup.kkyuni.feature.login.domain.source.LoginPreferenceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginPreferenceModule {

    @Binds
    abstract fun bindsLoginPreferenceManager(manager: LoginPreferenceManagerImpl): LoginPreferenceManager
}