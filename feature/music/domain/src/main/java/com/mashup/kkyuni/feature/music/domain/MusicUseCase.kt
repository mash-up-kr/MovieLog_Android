package com.mashup.kkyuni.feature.music.domain

import javax.inject.Inject

class MusicUseCase @Inject constructor (private val musicRepository: MusicRepository) {
    suspend operator fun invoke() {

    }
}