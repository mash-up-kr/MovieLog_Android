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
abstract class GoogleLoginRepositoryModule {

    @Binds
    abstract fun bindsGoogleLoginRepository(repository: GoogleLoginRepositoryImpl): GoogleLoginRepository

    @Binds
    abstract fun bindGoogleLoginAuthInfoDataSource(dataSource: GoogleLoginAuthInfoDataSourceImpl): GoogleLoginAuthInfoDataSource

    @Binds
    abstract fun bindGoogleLoginAuthInfoRepository(repository: GoogleLoginAuthInfoRepositoryImpl): GoogleLoginAuthInfoRepository
}