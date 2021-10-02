package com.mashup.kkyuni.playlist.data.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PlayListResponseDto(
    @Json(name = "result")
    val result: Int,
    @Json(name = "data")
    val data: List<PlayListDto>
)

@Keep
@JsonClass(generateAdapter = true)
data class PlayListDto(
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String,
    @Json(name = "singer")
    val singer: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "playTime")
    val playTime: String,
    @Json(name = "releaseDate")
    val releaseDate: String,
    @Json(name = "linkUrl")
    val linkUrl: String
)