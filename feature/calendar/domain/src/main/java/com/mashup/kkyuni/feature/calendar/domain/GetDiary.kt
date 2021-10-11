package com.mashup.kkyuni.feature.calendar.domain

import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity
import javax.inject.Inject

class GetDiary @Inject constructor(private val calendarRepository: CalendarRepository) {

    suspend operator fun invoke(date: String): DiaryEntity {
        return calendarRepository.getDiary(date)
    }
}