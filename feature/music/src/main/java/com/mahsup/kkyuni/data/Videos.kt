package com.mahsup.kkyuni.data

data class Videos(
    val kind: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val pageInfo: VideoPageInfo,
    val items: List<Video>
)
