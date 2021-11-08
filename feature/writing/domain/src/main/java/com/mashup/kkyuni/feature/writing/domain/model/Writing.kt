package com.mashup.kkyuni.feature.writing.domain.model

import com.mashup.kkyuni.core.constant.Constant.Emotion

data class Writing (
    val date: String? = null,
    val emotion: Emotion? = null,
    val music: Music? = null,
    val title: String? = null,
    val content: String? = null,
    val type: String? = null
)

data class Music(
    val thumbnailUrl: String,
    val title: String,
    val linkUrl: String,
    val playTime: String?,
    val releaseDate: String?
)

