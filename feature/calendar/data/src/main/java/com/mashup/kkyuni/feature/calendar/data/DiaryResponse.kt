package com.mashup.kkyuni.feature.calendar.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DiaryResponse(
    @Json(name = "content")
    val content: String,
    @Json(name = "diaryId")
    val diaryId: Int? = null,
    @Json(name = "diaryType")
    val diaryType: String,
    @Json(name = "emotion")
    val emotion: String,
    @Json(name = "musicPlayTime")
    val musicPlayTime: Int,
    @Json(name = "musicThumbnailImage")
    val musicThumbnailImage: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "writingDate")
    val writingDate: String,
    @Json(name = "youtubeLink")
    val youtubeLink: String
)
