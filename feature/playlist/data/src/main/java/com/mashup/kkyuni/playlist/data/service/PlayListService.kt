package com.mashup.kkyuni.playlist.data.service

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayListService {
    @GET("getPlayList")
    suspend fun fetchPlayList(
        @Query("date")
        date: String
    ): PlayListResponseDto
}