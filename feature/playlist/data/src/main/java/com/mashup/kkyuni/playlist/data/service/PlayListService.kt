package com.mashup.kkyuni.playlist.data.service

import com.mashup.kkyuni.playlist.data.dto.PlayListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayListService {
    @GET("diary/{date}/list")
    suspend fun fetchPlayList(
        @Path("date")
        date: String
    ): List<PlayListDto>
}