package com.mashup.kkyuni.core.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AuthPreferenceModule {

    @Binds
    abstract fun bindsAuthPreference(authPreference: AuthPreferenceImpl): AuthPreference
}
