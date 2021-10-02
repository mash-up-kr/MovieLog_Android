package com.mashup.kkyuni.playlist.data.repository

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import kotlinx.coroutines.flow.Flow

interface PlayListRepository {
    fun fetchPlayList(
        date: String
    ): Flow<PlayListResponseDto>
}
