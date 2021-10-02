package com.mashup.kkyuni.playlist.data.service

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PlayListService {
    @GET("getPlayList")
    fun fetchPlayList(): Flow<PlayListResponseDto>
}