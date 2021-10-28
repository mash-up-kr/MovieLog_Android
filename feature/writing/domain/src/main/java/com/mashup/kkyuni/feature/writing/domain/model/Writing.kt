package com.mashup.kkyuni.feature.writing.domain.model

import com.mashup.kkyuni.core.constant.Constant.Emotion

data class Writing (
    val date: String,
    val emotion: Emotion,
    val music: Music,
    val title: String,
    val content: String
)

data class Music(
    val thumbnailUrl: String,
    val title: String,
    val playTime: String,
    val releaseDate: String,
    val linkUrl: String
)

