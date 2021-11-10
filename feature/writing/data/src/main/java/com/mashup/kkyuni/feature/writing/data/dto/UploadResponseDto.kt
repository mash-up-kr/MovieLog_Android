package com.mashup.kkyuni.feature.writing.data.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class UploadResponseDto (
    @Json(name = "content")
    val content: String,
    @Json(name = "diaryId")
    val diaryId: Int,
    @Json(name = "diaryType")
    val diaryType: String,
    @Json(name = "emotion")
    val emotion: String,
    @Json(name = "musicPlayTime")
    val musicPlayTime: Int,
    @Json(name = "musicThumbnailImage")
    val musicThumbnailImage: String,
    @Json(name = "musicTitle")
    val musicTitle: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "writingDate")
    val writingDate: String,
    @Json(name = "youtubeLink")
    val youtubeLink: String
)