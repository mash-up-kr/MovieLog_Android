package com.mashup.kkyuni.feature.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideosResponse(
    @Json(name = "kind") val kind: String,
    @Json(name = "nextPageToken") val nextPageToken: String,
    @Json(name = "prevPageToken") val prevPageToken: String,
    @Json(name = "pageInfo") val pageInfoResponse: VideoPageInfoResponse,
    @Json(name = "items") val items: List<VideoResponse>
)
