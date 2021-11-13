package com.mashup.kkyuni.feature.calendar.domain

import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {

    operator fun invoke(): String? {
        return calendarRepository.getAccessToken()
    }
}