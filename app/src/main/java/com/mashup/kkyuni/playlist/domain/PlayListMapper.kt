package com.mashup.kkyuni.playlist.domain

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import com.mashup.kkyuni.playlist.domain.model.PlayList

fun PlayListResponseDto.toPlayList(): List<PlayList> {
    return if(data.isEmpty()){
        listOf(PlayList.EmptyData)
    }else {
        data.map {
            PlayList.MusicData(
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