package com.mashup.kkyuni.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoId(
    @Json(name="kind") val kind: String,
    @Json(name="videoId") val videoId: String,
    @Json(name="channelId") val channelId: String,
    @Json(name="playlistId") val playlistId: String
)
