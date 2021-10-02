package com.mashup.kkyuni.playlist.data.repository

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import com.mashup.kkyuni.playlist.domain.repository.PlayListRepository
import com.mashup.kkyuni.playlist.data.service.PlayListService
import kotlinx.coroutines.flow.Flow

class PlayListRepositoryImpl(
    private val service: PlayListService
): PlayListRepository {

    override fun fetchPlayList(): Flow<PlayListResponseDto> =
        service.fetchPlayList()
}