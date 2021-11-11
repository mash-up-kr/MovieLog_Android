package com.mashup.kkyuni.feature.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoIdResponse(
    @Json(name = "kind") val kind: String,
    @Json(name = "videoId") val videoId: String,
)
