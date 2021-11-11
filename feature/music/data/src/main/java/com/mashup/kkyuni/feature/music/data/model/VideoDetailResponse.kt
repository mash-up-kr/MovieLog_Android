package com.mashup.kkyuni.feature.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoDetailResponse(
    @Json(name = "id") val id: String,
    @Json(name = "contentDetails") val contentDetails: ContentDetailResponse,
)