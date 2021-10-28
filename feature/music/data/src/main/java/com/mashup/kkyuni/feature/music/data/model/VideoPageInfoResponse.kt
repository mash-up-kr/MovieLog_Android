package com.mashup.kkyuni.feature.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoPageInfoResponse(
    @Json(name="totalResults") val totalResults: Int,
    @Json(name="resultsPerPage") val resultsPerPage: Int
)
