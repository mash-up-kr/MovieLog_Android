package com.mashup.kkyuni.playlist.data.repository

import com.mashup.kkyuni.playlist.data.dto.PlayListDto
import kotlinx.coroutines.flow.Flow

interface PlayListRepository {
    suspend fun fetchPlayList(
        date: String
    ): Flow<List<PlayListDto>>
}
