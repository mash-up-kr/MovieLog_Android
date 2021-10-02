package com.mashup.kkyuni.feature.music.domain.model

data class VideoId(
    val kind: String,
    val videoId: String,
    val channelId: String,
    val playlistId: String
)
