package com.mashup.kkyuni.feature.writing.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.core.constant.Constant
import com.mashup.kkyuni.feature.writing.domain.model.Music
import com.mashup.kkyuni.feature.writing.domain.model.Writing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingViewModel @Inject constructor(): ViewModel() {
    private val _writing = MutableLiveData<Writing>()
    val writing get() = _writing
    init {
        _writing.value = Writing()
    }

    private val _isSetMusic = Transformations.map(_writing) {
        it.music != null
    }
    val isSetMusic get() = _isSetMusic

    fun getCurrentWriting() = _writing.value ?: Writing()

    fun updateEmotion(emotion: Constant.Emotion) {
        _writing.value = _writing.value?.copy(
            emotion = emotion
        )
    }

    fun updateMusic(music: Music) {
        _writing.value = _writing.value?.copy(
            music = music
        )
    }

    fun updateTitle(title: String) {
        _writing.value = _writing.value?.copy(
            title = title
        )
    }

    fun updateContent(content: String) {
        _writing.value = _writing.value?.copy(
            content = content
        )
    }
}