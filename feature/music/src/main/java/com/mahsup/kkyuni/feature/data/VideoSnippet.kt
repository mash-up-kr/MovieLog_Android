package com.mahsup.kkyuni.feature.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoSnippet(
    @Json(name="publishedAt") val publishedAt: String,
    @Json(name="channelId") val channelId: String,
    @Json(name="title") val title: String,
    @Json(name="description") val description: String,
    @Json(name="thumbnails") val thumbnails: VideoThumbnail,
    @Json(name="channelTitle") val channelTitle: String
)