package com.mashup.kkyuni.feature.writing.domain.model

import com.mashup.kkyuni.core.constant.Constant.Emotion
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
data class Writing(
    val date: String? = null,
    val emotion: Emotion? = null,
    val music: Music? = null,
    val title: String? = null,
    val content: String? = null,
    val type: String? = null
) {
    companion object {
        private val jsonAdapter = Moshi.Builder().build().adapter(Writing::class.java)
    }

    fun toJson(): String = jsonAdapter.toJson(this)
}

@JsonClass(generateAdapter = true)
data class Music(
    val thumbnailUrl: String,
    val title: String,
    val linkUrl: String,
    val playTime: String?,
    val releaseDate: String?
)
