package com.mashup.kkyuni.feature.calendar.data.mapper

import com.mashup.kkyuni.feature.calendar.data.DiaryResponse
import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity

fun DiaryResponse.toEntity() = DiaryEntity(
    content,
    diaryId ?: -1,
    diaryType,
    emotion,
    musicPlayTime,
    musicThumbnailImage,
    title,
    writingDate,
    youtubeLink
)
