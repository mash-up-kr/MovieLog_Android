package com.mahsup.kkyuni.data

data class VideoSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: VideoThumbnail,
    val channelTitle: String
)