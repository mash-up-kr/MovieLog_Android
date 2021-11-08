package com.mashup.kkyuni.feature.writing.domain.model

data class Diary(
    val content: String,
    val diaryID: Int,
    val diaryType: String,
    val emotion: String,
    val musicPlayTime: Int,
    val musicThumbnailImage: String,
    val musicTitle: String,
    val title: String,
    val writingDate: String,
    val youtubeLink: String
)