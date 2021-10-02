package com.mashup.kkyuni.playlist.data.repository.impl

import com.mashup.kkyuni.playlist.data.dto.PlayListResponseDto
import com.mashup.kkyuni.playlist.data.repository.PlayListRepository
import com.mashup.kkyuni.playlist.data.service.PlayListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class PlayListRepositoryImpl(
    private val service: PlayListService
): PlayListRepository {

    override fun fetchPlayList(
        date: String
    ): Flow<PlayListResponseDto> =
        flow {
            emit(service.fetchPlayList(date))
        }.flowOn(Dispatchers.IO)
}