package com.mashup.kkyuni.feature.calendar.data

import retrofit2.http.GET
import retrofit2.http.Path

interface DiaryService {

    @GET("/api/v1/diary/{date}")
    suspend fun getDiary(@Path("date") date: String) :DiaryResponse
}