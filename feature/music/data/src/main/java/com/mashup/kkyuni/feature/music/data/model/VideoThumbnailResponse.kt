package com.mashup.kkyuni.feature.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoThumbnailResponse(
    @Json(name = "default") val default: ThumbnailUrlResponse,
    @Json(name = "medium") val medium: ThumbnailUrlResponse,
)
