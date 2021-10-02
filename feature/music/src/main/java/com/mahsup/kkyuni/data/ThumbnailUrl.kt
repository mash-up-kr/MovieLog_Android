package com.mahsup.kkyuni.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailUrl(
    @Json(name="url") val url: String,
    @Json(name="width") val width: Int,
    @Json(name="height") val height: Int
)
