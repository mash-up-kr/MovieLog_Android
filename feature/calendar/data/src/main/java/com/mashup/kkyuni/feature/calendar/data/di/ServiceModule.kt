package com.mashup.kkyuni.feature.calendar.data.di

import com.mashup.kkyuni.feature.calendar.data.CalendarService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideCalendarService(@Named("kkyuni_api") retrofit: Retrofit) =
        retrofit.create(CalendarService::class.java)
}
