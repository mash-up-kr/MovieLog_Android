package com.mashup.kkyuni.feature.calendar.domain

import javax.inject.Inject

class CalendarUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    suspend operator fun invoke() {

    }
}