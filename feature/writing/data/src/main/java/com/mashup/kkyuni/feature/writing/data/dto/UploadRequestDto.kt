package com.mashup.kkyuni.feature.writing.data.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
class UploadRequestDto (
    @Json(name = "content")
    val content: String,
    @Json(name = "diaryType")
    val diaryType: String,
    @Json(name = "emotion")
    val emotion: String,
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String,
    @Json(name = "musicPlayTime")
    val musicPlayTime: Int,
    @Json(name = "musicTitle")
    val musicTitle: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "webViewURL")
    val url: String,
    @Json(name = "youtubeLink")
    val youtubeLink: String
)