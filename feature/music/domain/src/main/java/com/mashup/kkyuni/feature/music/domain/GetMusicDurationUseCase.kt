package com.mashup.kkyuni.feature.music.domain

import com.mashup.kkyuni.feature.music.domain.model.VideoDetails
import javax.inject.Inject

class GetMusicDurationUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(id: String): VideoDetails {
        return musicRepository.getDuration(id)
    }
}