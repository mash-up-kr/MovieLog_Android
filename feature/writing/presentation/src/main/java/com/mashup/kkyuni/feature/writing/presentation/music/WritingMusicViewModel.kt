package com.mashup.kkyuni.feature.writing.presentation.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingMusicViewModel @Inject constructor(): ViewModel() {
    private val _backEvent = MutableSharedFlow<Unit>()
    val backEvent = _backEvent.asSharedFlow()

    private val _searchEvent = MutableSharedFlow<Unit>()
    val searchEvent = _searchEvent.asSharedFlow()

    fun onClickedBack() = viewModelScope.launch {
        _backEvent.emit(Unit)
    }

    fun onClickedSearch() = viewModelScope.launch {
        _searchEvent.emit(Unit)
    }
}