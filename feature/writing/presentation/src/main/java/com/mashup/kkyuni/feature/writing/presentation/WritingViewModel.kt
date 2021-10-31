package com.mashup.kkyuni.feature.writing.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.core.constant.Constant
import com.mashup.kkyuni.feature.writing.domain.model.Writing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingViewModel @Inject constructor(): ViewModel() {
    private val _writing = MutableStateFlow(Writing())
    val writing = _writing.asStateFlow()

    fun updateEmotion(emotion: Constant.Emotion){
        _writing.update {
            _writing.value.copy(
                emotion = emotion
            )
        }
    }
}