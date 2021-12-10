package com.mashup.kkyuni.playlist.data.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PlayListDto(
    @Json(name = "content")
    val content: String,
    @Json(name = "diaryID")
    val diaryID: Int,
    @Json(name = "diaryType")
    val diaryType: String,
    @Json(name = "emotion")
    val emotion: String,
    @Json(name = "musicPlayTime")
    val musicPlayTime: Int,
    @Json(name = "musicThumbnailImageUrl")
    val musicThumbnailImageUrl: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "writingDate")
    val writingDate: String,
    @Json(name = "youtubeLink")
    val youtubeLink: String
)