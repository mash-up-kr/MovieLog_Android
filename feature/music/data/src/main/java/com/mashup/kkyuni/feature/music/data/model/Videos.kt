package com.mashup.kkyuni.music.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Videos(
    @Json(name = "kind") val kind: String,
    @Json(name = "nextPageToken") val nextPageToken: String,
    @Json(name = "prevPageToken") val prevPageToken: String,
    @Json(name = "pageInfo") val pageInfo: VideoPageInfo,
    @Json(name = "items") val items: List<Video>
)
