package com.mashup.kkyuni.data.network.auth

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface AbstractModule {
        @Binds
        fun bindsMovieLogAuthenticator(
            authenticator: MovieLogAuthenticator
        ): Authenticator
    }

    @Provides
    fun providesAuthService(@Named("kkyuni_other_api") retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}