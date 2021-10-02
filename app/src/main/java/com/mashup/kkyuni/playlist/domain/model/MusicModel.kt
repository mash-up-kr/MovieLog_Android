package com.mashup.kkyuni.playlist.domain.model

sealed class MusicModel {

    object EmptyData: MusicModel()

    data class MusicData(
        val thumbnailUrl: String,
        val singer: String,
        val title: String,
        val playTime: String,
        val releaseDate: String,
        val linkUrl: String
    ): MusicModel()
}