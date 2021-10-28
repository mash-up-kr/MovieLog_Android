package com.mashup.kkyuni.feature.music.domain.model

data class Videos(
    val kind: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val pageInfo: VideoPageInfo,
    val items: List<Video>
)
