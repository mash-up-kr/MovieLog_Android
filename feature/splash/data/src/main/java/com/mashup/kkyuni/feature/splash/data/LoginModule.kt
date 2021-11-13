package com.mashup.kkyuni.feature.splash.data

import com.mashup.kkyuni.feature.splash.domain.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Module
    @InstallIn(ViewModelComponent::class)
    interface AbstractModule {

        @Binds
        fun bindsLoginRepository(repository: LoginRepositoryImpl): LoginRepository
    }

    @Provides
    fun provideLoginService(@Named("kkyuni_other_api") retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)
}