package com.mashup.kkyuni.feature.writing.presentation.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.writing.domain.model.Music
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WritingMusicViewModel: ViewModel() {
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