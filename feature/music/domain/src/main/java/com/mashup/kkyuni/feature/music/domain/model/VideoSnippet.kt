package com.mashup.kkyuni.feature.music.domain.model

data class VideoSnippet(
    val publishedAt: String,
    val title: String,
    val description: String,
    val thumbnails: VideoThumbnail,
    val channelTitle: String,
)