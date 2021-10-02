package com.mashup.kkyuni.feature.calendar.data

import com.mashup.kkyuni.feature.calendar.domain.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val service: CalendarService
) : CalendarRepository {

}
