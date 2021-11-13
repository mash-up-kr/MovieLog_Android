package com.mashup.kkyuni.feature.calendar.domain

import javax.inject.Inject

class GetPreviewUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {

    operator fun invoke(): Boolean {
        return calendarRepository.getPreview()
    }
}