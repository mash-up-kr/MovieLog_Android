package com.mashup.kkyuni.feature.music.presentation

import androidx.lifecycle.ViewModel
import com.mashup.kkyuni.feature.music.domain.MusicRepository
import javax.inject.Inject

class MusicViewModel @Inject constructor(private val repository: MusicRepository): ViewModel() {

    fun search(query: String) {

    }
}