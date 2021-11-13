package com.mashup.kkyuni.data.network

import com.mashup.kkyuni.data.network.auth.MovieLogAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/"
    private val BASE_API_URL = "http://3.37.106.181:8080/api/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("movie_log_client")
    fun provideMovieLogOkHttpClient(movieLogAuthenticator: Authenticator): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .authenticator(movieLogAuthenticator)
            .build()
    }

    @Named("youtube_api")
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(YOUTUBE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Named("kkyuni_api")
    @Provides
    @Singleton
    fun provideKKyuniRetrofit(@Named("movie_log_client") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Named("kkyuni_other_api")
    @Provides
    @Singleton
    fun provideOtherRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}