package com.mashup.kkyuni.feature.playlist.domain

import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.playlist.data.dto.PlayListDto

fun List<PlayListDto>.toPlayList(): List<MusicModel> {
    return if (isEmpty()) {
        listOf(MusicModel.EmptyData)
    } else {
        map {
            MusicModel.MusicData(
                thumbnailUrl = it.musicThumbnailImageUrl,
                title = it.title,
                playTime = it.musicPlayTime.toString(),
                releaseDate = it.writingDate,
                linkUrl = it.youtubeLink,
                emotion = it.emotion
            )
        }
    }
}