package com.mashup.kkyuni.feature.music.domain.model

data class Video(
    val kind: String,
    val id: VideoId,
    val snippet: VideoSnippet,
    var duration: String = "",
)