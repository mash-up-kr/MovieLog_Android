package com.mashup.kkyuni.feature.writing.presentation.content

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingContentViewModel @Inject constructor(): ViewModel() {
    private val _content = MutableLiveData<String>()
    val content: LiveData<String> get() = _content

    private val _isSetContent = Transformations.map(_content){
        it.isNotEmpty()
    }
    val isSetContent: LiveData<Boolean> get() = _isSetContent

    private val _contentLength = Transformations.map(_content){
        _content.value?.length ?: 0
    }
    val contentLength: LiveData<Int> get() = _contentLength

    private val _nextEvent = MutableSharedFlow<Unit>()
    val nextEvent = _nextEvent

    private val _backEvent = MutableSharedFlow<Unit>()
    val backEvent = _backEvent.asSharedFlow()

    fun updateContent(content: String){
        _content.value = content
    }

    fun onClickedNext(){
        viewModelScope.launch {
            _nextEvent.emit(Unit)
        }
    }

    fun onClickedBack() {
        viewModelScope.launch {
            _backEvent.emit(Unit)
        }
    }
}