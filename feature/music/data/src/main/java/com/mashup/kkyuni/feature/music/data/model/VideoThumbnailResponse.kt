package com.mashup.kkyuni.music.data.model

import com.mashup.kkyuni.feature.music.data.model.ThumbnailUrlResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoThumbnailResponse(
    @Json(name="default") val default: ThumbnailUrlResponse,
    @Json(name="medium") val medium: ThumbnailUrlResponse,
    @Json(name="large") val large: ThumbnailUrlResponse
)
