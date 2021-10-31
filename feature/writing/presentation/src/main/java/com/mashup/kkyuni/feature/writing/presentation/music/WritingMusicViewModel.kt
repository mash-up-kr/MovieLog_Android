package com.mashup.kkyuni.feature.writing.presentation.music

import androidx.lifecycle.ViewModel
import com.mashup.kkyuni.feature.writing.domain.model.Music
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class WritingMusicViewModel: ViewModel() {
    private val _music = MutableStateFlow<Music?>(null)
    val music = _music.asStateFlow()

}