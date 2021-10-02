package com.mahsup.kkyuni.feature.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoPageInfo(
    @Json(name="totalResults") val totalResults: Int,
    @Json(name="resultsPerPage") val resultsPerPage: Int
)
