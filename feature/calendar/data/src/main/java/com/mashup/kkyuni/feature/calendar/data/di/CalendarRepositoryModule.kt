package com.mashup.kkyuni.feature.calendar.data.di

import com.mashup.kkyuni.feature.calendar.domain.CalendarRepository
import com.mashup.kkyuni.feature.calendar.data.CalendarRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @InstallIn(ViewModelComponent::class)
    @Module
    interface AbstractModule {
        @Binds
        fun bindsCalendarRepository(repositoryImpl: CalendarRepositoryImpl): CalendarRepository
    }
}