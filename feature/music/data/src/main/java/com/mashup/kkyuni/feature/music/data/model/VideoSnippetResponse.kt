package com.mashup.kkyuni.feature.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoSnippetResponse(
    @Json(name = "publishedAt") val publishedAt: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnails") val thumbnails: VideoThumbnailResponse,
    @Json(name = "channelTitle") val channelTitle: String,
)