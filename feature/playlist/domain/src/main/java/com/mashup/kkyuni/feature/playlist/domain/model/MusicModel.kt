package com.mashup.kkyuni.feature.playlist.domain.model

sealed class MusicModel {

    object EmptyData : MusicModel()

    data class MusicData(
        val thumbnailUrl: String,
        val title: String,
        val playTime: String,
        val releaseDate: String,
        val linkUrl: String,
        val emotion: String
    ) : MusicModel()
}