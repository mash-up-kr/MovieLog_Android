package com.mashup.kkyuni.feature.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoResponse(
    @Json(name = "kind") val kind: String,
    @Json(name = "id") val idResponse: VideoIdResponse,
    @Json(name = "snippet") val snippet: VideoSnippetResponse
)