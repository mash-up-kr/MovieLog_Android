package com.mashup.kkyuni.playlist.data.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PlayListResponseDto(
    @Json(name = "data")
    val data: List<PlayListDto>
)

@Keep
@JsonClass(generateAdapter = true)
data class PlayListDto(
    @Json(name = "musicThumbnailImage")
    val musicThumbnailImage: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "musicPlayTime")
    val musicPlayTime: String,
    @Json(name = "writingDate")
    val writingDate: String,
    @Json(name = "youtubeLink")
    val youtubeLink: String
)