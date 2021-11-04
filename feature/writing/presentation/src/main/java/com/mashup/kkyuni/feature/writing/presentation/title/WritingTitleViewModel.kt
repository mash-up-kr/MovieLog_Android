package com.mashup.kkyuni.feature.writing.presentation.title

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingTitleViewModel @Inject constructor(): ViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _isSetTitle = Transformations.map(_title){
        it.isNotEmpty()
    }
    val isSetTitle: LiveData<Boolean> get() = _isSetTitle

    private val _titleLength = Transformations.map(_title){
        _title.value?.length ?: 0
    }
    val titleLength: LiveData<Int> get() = _titleLength

    private val _nextEvent = MutableSharedFlow<Unit>()
    val nextEvent = _nextEvent

    private val _backEvent = MutableSharedFlow<Unit>()
    val backEvent = _backEvent.asSharedFlow()


    fun updateTitle(title: String){
        _title.value = title
    }

    fun onNext(){
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