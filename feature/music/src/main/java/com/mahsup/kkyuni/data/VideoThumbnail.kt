package com.mahsup.kkyuni.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoThumbnail(
    @Json(name="default") val default: ThumbnailUrl,
    @Json(name="medium") val medium: ThumbnailUrl,
    @Json(name="large") val large: ThumbnailUrl
)
