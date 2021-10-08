package com.mashup.kkyuni.playlist.data.repository.impl

import com.mashup.kkyuni.playlist.data.dto.PlayListDto
import com.mashup.kkyuni.playlist.data.repository.PlayListRepository
import com.mashup.kkyuni.playlist.data.service.PlayListService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PlayListRepositoryImpl @Inject constructor(
    private val service: PlayListService
): PlayListRepository {

    override suspend fun fetchPlayList(
        date: String
    ): Flow<List<PlayListDto>> = flow {
        emit(service.fetchPlayList(date))
    }
}