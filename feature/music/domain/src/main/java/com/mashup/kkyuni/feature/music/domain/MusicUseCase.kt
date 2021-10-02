package com.mashup.kkyuni.feature.music.domain

import javax.inject.Inject

class GetMusicUseCase @Inject constructor (private val musicRepository: MusicRepository) {
    suspend operator fun invoke() {
        musicRepository
    }
}