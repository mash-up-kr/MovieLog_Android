package com.mashup.kkyuni.feature.writing.domain

import com.mashup.kkyuni.feature.writing.data.dto.UploadResponseDto
import com.mashup.kkyuni.feature.writing.domain.model.Diary

fun UploadResponseDto.toDiary(): Diary {
    return Diary(
        content = content,
        diaryType = diaryType,
        emotion = emotion,
        musicPlayTime = musicPlayTime,
        musicThumbnailImage = musicThumbnailImage,
        musicTitle = musicTitle,
        title = title,
        youtubeLink = youtubeLink,
        writingDate = writingDate,
        diaryID = diaryId
    )
}