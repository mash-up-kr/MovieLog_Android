package com.mashup.kkyuni.playlist.domain.usecase

import com.mashup.kkyuni.playlist.data.repository.PlayListRepository
import com.mashup.kkyuni.playlist.domain.model.PlayList
import com.mashup.kkyuni.playlist.domain.toPlayList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPlayListUseCase(
    private val playListRepository: PlayListRepository
) {

    operator fun invoke(): Flow<List<PlayList>> {
        return playListRepository.fetchPlayList()
            .map { it.toPlayList() }

    }
}