package com.mashup.kkyuni.feature.calendar.data

import com.mashup.kkyuni.feature.calendar.data.mapper.toEntity
import com.mashup.kkyuni.feature.calendar.domain.CalendarRepository
import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val service: DiaryService
) : CalendarRepository {

    override suspend fun getDiary(date: String): DiaryEntity {
        return service.getDiary(date).toEntity()
    }
}
