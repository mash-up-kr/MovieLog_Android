package com.mashup.kkyuni.feature.music.domain

import com.mashup.kkyuni.feature.music.domain.model.VideoDetails
import com.mashup.kkyuni.feature.music.domain.model.Videos

interface MusicRepository {
    suspend fun getMusic(query: String): Videos

    suspend fun getDuration(videoId: String): VideoDetails
}