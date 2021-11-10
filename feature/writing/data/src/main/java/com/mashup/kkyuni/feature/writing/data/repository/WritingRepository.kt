package com.mashup.kkyuni.feature.writing.data.repository

import com.mashup.kkyuni.feature.writing.data.dto.UploadResponseDto
import kotlinx.coroutines.flow.Flow

interface WritingRepository {
    fun createDiary(
        content: String,
        diaryType: String,
        emotion: String,
        musicPlayTime: Int,
        musicThumbnailImage: String,
        musicTitle: String,
        title: String,
        youtubeLink: String
    ): Flow<UploadResponseDto>
}