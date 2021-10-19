package com.mashup.kkyuni.feature.login.data.di

import com.mashup.kkyuni.feature.login.data.source.GoogleLoginAuthInfoDataSourceImpl
import com.mashup.kkyuni.feature.login.data.source.GoogleLoginAuthInfoRepositoryImpl
import com.mashup.kkyuni.feature.login.data.source.GoogleLoginRepositoryImpl
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoDataSource
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoRepository
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class GoogleLoginRepositoryModule {

    @Module
    @InstallIn(ViewModelComponent::class)
    interface AbstractModule {
        @Binds
        fun bindsGoogleLoginRepository(repository: GoogleLoginRepositoryImpl): GoogleLoginRepository

        @Binds
        fun bindGoogleLoginAuthInfoDataSource(dataSource: GoogleLoginAuthInfoDataSourceImpl): GoogleLoginAuthInfoDataSource

        @Binds
        fun bindGoogleLoginAuthInfoRepository(repository: GoogleLoginAuthInfoRepositoryImpl): GoogleLoginAuthInfoRepository
    }

//    @Provides
//    fun provideGoogleLoginRepository(
//        signInClient: SignInClient, signInRequest: BeginSignInRequest
//    ): GoogleLoginRepository = GoogleLoginRepositoryImpl(signInClient, signInRequest)

//    @Provides
//    fun provideGoogleLoginAuthInfoDataSource(
//        loginPreferenceManager: LoginPreferenceManager
//    ): GoogleLoginAuthInfoDataSource = GoogleLoginAuthInfoDataSourceImpl(loginPreferenceManager)
//
//    @Provides
//    fun provideGoogleLoginAuthRepository(
//        googleLoginService: GoogleLoginService
//    ): GoogleLoginAuthInfoRepository = GoogleLoginAuthInfoRepositoryImpl(googleLoginService)
}