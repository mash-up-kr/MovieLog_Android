package com.mashup.kkyuni.feature.login.data

import com.mashup.kkyuni.feature.login.domain.GoogleLoginRepository
import dagger.Binds
import dagger.Module
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
}