package com.mashup.kkyuni.feature.writing.presentation.emotion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.core.constant.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingEmotionViewModel @Inject constructor(): ViewModel() {
    private val _emotion = MutableStateFlow(Constant.Emotion.UNKNOWN)
    val emotion = _emotion.asStateFlow()

    val bottomButtonEnabled  = _emotion.map {
            it != Constant.Emotion.UNKNOWN
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            false
        )

    private val _nextEvent = MutableSharedFlow<Unit>()
    val nextEvent = _nextEvent.asSharedFlow()

    fun onSelectedMad() {
        viewModelScope.launch {
            _emotion.emit(Constant.Emotion.MAD)
        }
    }

    fun onSelectedHappy(){
        viewModelScope.launch {
            _emotion.emit(Constant.Emotion.HAPPY)
        }
    }

    fun onSelectedNormal(){
        viewModelScope.launch {
            _emotion.emit(Constant.Emotion.NORMAL)
        }
    }

    fun onSelectedPanic(){
        viewModelScope.launch {
            _emotion.emit(Constant.Emotion.PANIC)
        }
    }

    fun onSelectedSad(){
        viewModelScope.launch {
            _emotion.emit(Constant.Emotion.SAD)
        }
    }

    fun onClickedNext(){
        viewModelScope.launch {
            _nextEvent.emit(Unit)
        }
    }
}