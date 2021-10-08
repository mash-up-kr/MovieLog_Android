package com.mashup.kkyuni.feature.playlist.domain.usecase

import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.feature.playlist.domain.toPlayList
import com.mashup.kkyuni.playlist.data.repository.PlayListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPlayListUseCase @Inject constructor(
    private val playListRepository: PlayListRepository
) {

    data class Params(
        val year: Int,
        val month: Int
    )

    suspend operator fun invoke(
        params: Params,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<MusicModel>> {
        return playListRepository.fetchPlayList(
            date = "${params.year}-${params.month}"
        ).map {
            it.toPlayList()
        }.onStart {
            onStart()
        }.onCompletion {
            onComplete()
        }.catch { throwable ->
            onError(throwable.message)
        }.flowOn(Dispatchers.IO)
    }
}