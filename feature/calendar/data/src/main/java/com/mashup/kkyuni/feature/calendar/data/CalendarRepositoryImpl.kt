package com.mashup.kkyuni.feature.calendar.data

import android.util.Log
import com.mashup.kkyuni.feature.calendar.data.mapper.toEntity
import com.mashup.kkyuni.feature.calendar.domain.CalendarRepository
import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val service: DiaryService
) : CalendarRepository {

    override suspend fun getDiary(date: String): DiaryEntity {
        kotlin.runCatching {
            service.getDiary(date).toEntity()
        }.onFailure {
            Log.v("test", it.toString())
        }
        return service.getDiary(date).toEntity()
    }
}
