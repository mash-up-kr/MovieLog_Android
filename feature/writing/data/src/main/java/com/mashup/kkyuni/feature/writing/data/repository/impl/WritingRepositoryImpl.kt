package com.mashup.kkyuni.feature.writing.data.repository.impl

import com.mashup.kkyuni.feature.writing.data.dto.UploadRequestDto
import com.mashup.kkyuni.feature.writing.data.repository.WritingRepository
import com.mashup.kkyuni.feature.writing.data.service.WritingService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WritingRepositoryImpl @Inject constructor(
    private val service: WritingService
) : WritingRepository {

    override fun createDiary(
        content: String,
        diaryType: String,
        emotion: String,
        musicPlayTime: Int,
        musicThumbnailImage: String,
        musicTitle: String,
        title: String,
        youtubeLink: String,
        writingDate: String,
        latitude: String,
        longitude: String
    ) = flow {
        emit(
            service.createDiary(
                UploadRequestDto(
                    content,
                    diaryType,
                    emotion,
                    latitude,
                    longitude,
                    musicPlayTime,
                    musicThumbnailImage,
                    musicTitle,
                    title,
                    "unknown",
                    writingDate,
                    youtubeLink
                )
            )
        )
    }
}