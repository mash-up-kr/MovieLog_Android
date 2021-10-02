package com.mashup.kkyuni.playlist.domain.repository

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import kotlinx.coroutines.flow.Flow

interface PlayListRepository {
    fun fetchPlayList(
        date: String
    ): Flow<PlayListResponseDto>
}
