package com.mashup.kkyuni.feature.calendar.domain.model

class DiaryEntity(
    val content: String,
    val diaryId: Int,
    val diaryType: String,
    val emotion: String,
    val musicPlayTime: Int,
    val musicThumbnailImage: String,
    val title: String,
    val writingDate: String,
    val youtubeLink: String
)