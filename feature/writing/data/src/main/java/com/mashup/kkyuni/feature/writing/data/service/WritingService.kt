package com.mashup.kkyuni.feature.writing.data.service

import com.mashup.kkyuni.feature.writing.data.dto.UploadRequestDto
import com.mashup.kkyuni.feature.writing.data.dto.UploadResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface WritingService {
    @POST("diary")
    suspend fun createDiary(
        @Body uploadRequestDto: UploadRequestDto
    ): UploadResponseDto
}