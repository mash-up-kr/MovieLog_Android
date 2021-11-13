package com.mashup.kkyuni.feature.calendar.domain

import javax.inject.Inject

class SetPreviewUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {

    operator fun invoke(boolean: Boolean) {
        calendarRepository.setPreview(boolean)
    }
}