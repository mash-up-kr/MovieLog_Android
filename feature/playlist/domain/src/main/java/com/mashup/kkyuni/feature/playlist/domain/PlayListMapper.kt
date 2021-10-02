package com.mashup.kkyuni.feature.playlist.domain

import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto

fun PlayListResponseDto.toPlayList(): List<MusicModel> {
    return if(data.isEmpty()){
        listOf(MusicModel.EmptyData)
    }else {
        data.map {
            MusicModel.MusicData(
                thumbnailUrl = it.thumbnailUrl,
                singer = it.singer,
                title = it.title,
                playTime = it.playTime,
                releaseDate = it.releaseDate,
                linkUrl = it.linkUrl
            )
        }
    }
}