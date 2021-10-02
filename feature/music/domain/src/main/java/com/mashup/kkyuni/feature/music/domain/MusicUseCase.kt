package com.mashup.kkyuni.feature.music.domain

import com.mashup.kkyuni.feature.music.domain.model.Videos
import javax.inject.Inject

class GetMusicUseCase @Inject constructor (private val musicRepository: MusicRepository) {
    suspend operator fun invoke(query: String): Videos {
        return musicRepository.getMusic(query)
    }
}