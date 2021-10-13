package com.mashup.kkyuni.feature.login.data.di

import com.mashup.kkyuni.feature.login.data.source.LoginPreferenceManager
import com.mashup.kkyuni.feature.login.data.source.GoogleLoginAuthInfoRepositoryImpl
import com.mashup.kkyuni.feature.login.data.source.GoogleLoginRepositoryImpl
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoRepository
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class GoogleLoginRepositoryModule {

    @InstallIn(ViewModelComponent::class)
    @Module
    interface AbstractModule {
        @Binds
        fun bindsGoogleLoginRepository(repository: GoogleLoginRepositoryImpl): GoogleLoginRepository

    }

    @Provides
    fun bindsGoogleLoginAuthInfoRepository(
        loginPreferenceManager: LoginPreferenceManager
    ): GoogleLoginAuthInfoRepository = GoogleLoginAuthInfoRepositoryImpl(loginPreferenceManager)
}