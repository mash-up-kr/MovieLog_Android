package com.mashup.kkyuni.playlist.domain

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import com.mashup.kkyuni.playlist.domain.model.MusicModel

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