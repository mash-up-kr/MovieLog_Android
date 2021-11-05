package com.mashup.kkyuni.feature.writing.presentation.emotion

import androidx.lifecycle.*
import com.mashup.kkyuni.core.constant.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.map
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

    private val _nextEvent = MutableSharedFlow<Constant.Emotion>()
    val nextEvent = _nextEvent.asSharedFlow()

    private val _backEvent = MutableSharedFlow<Unit>()
    val backEvent = _backEvent.asSharedFlow()

    fun setEmotion(emotion: Constant.Emotion){
        viewModelScope.launch {
            _emotion.emit(emotion)
        }
    }

    fun onSelectedMad() {
        setEmotion(
            if(_emotion.value == Constant.Emotion.MAD){
                Constant.Emotion.UNKNOWN
            }else {
                Constant.Emotion.MAD
            }
        )
    }

    fun onSelectedHappy(){
        setEmotion(
            if(_emotion.value == Constant.Emotion.HAPPY){
                Constant.Emotion.UNKNOWN
            }else {
                Constant.Emotion.HAPPY
            }
        )
    }

    fun onSelectedNormal(){
        setEmotion(
            if(_emotion.value == Constant.Emotion.NORMAL){
                Constant.Emotion.UNKNOWN
            }else {
                Constant.Emotion.NORMAL
            }
        )
    }

    fun onSelectedPanic(){
        setEmotion(
            if(_emotion.value == Constant.Emotion.PANIC){
                Constant.Emotion.UNKNOWN
            }else {
                Constant.Emotion.PANIC
            }
        )
    }

    fun onSelectedSad(){
        setEmotion(
            if(_emotion.value == Constant.Emotion.SAD){
                Constant.Emotion.UNKNOWN
            }else {
                Constant.Emotion.SAD
            }
        )
    }

    fun onClickedNext(){
        viewModelScope.launch {
            _nextEvent.emit(_emotion.value)
        }
    }

    fun onClickedBack(){
        viewModelScope.launch {
            _backEvent.emit(Unit)
        }
    }
}