package com.mashup.kkyuni.feature.login.data.di

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.mashup.kkyuni.feature.login.data.source.GoogleLoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class GoogleLoginModule {

    companion object {
        private const val WEB_CLIENT_ID = "924686911312-7ng4cnki9168sqvvlkc4i8dcf4tih8br.apps.googleusercontent.com"
    }

    @Provides
    fun provideSignInClient(@ApplicationContext context: Context): SignInClient = Identity.getSignInClient(context)

    @Provides
    fun provideBeginSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            .setAutoSelectEnabled(true)
            .build()
    }

    @Provides
    fun provideGoogleLoginService(@Named("kkyuni_api") retrofit: Retrofit): GoogleLoginService =
        retrofit.create(GoogleLoginService::class.java)
}