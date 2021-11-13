package com.mashup.kkyuni.feature.calendar.data

import com.mashup.kkyuni.core.auth.AuthPreference
import com.mashup.kkyuni.core.preference.SharedPreferenceManager
import com.mashup.kkyuni.feature.calendar.data.mapper.toEntity
import com.mashup.kkyuni.feature.calendar.domain.CalendarRepository
import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val service: DiaryService,
    private val preferenceManager: SharedPreferenceManager,
    private val authPreference: AuthPreference
) : CalendarRepository {

    override suspend fun getDiary(date: String): DiaryEntity {
        return service.getDiary(date).toEntity()
    }

    override fun setPreview(boolean: Boolean) {
        preferenceManager.setBoolean(preview, boolean)
    }

    override fun getPreview(): Boolean {
        return preferenceManager.getBoolean(preview, false)
    }

    override fun getAccessToken(): String? {
        return authPreference.getAccessToken()
    }

    private val preview = "preview"
}
