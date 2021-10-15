package com.mashup.kkyuni.feature.calendar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.calendar.domain.GetDiary
import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getDiary: GetDiary
) : ViewModel() {

    private val _onSetting = MutableSharedFlow<Unit>()
    val onSetting: SharedFlow<Unit> get() = _onSetting

    private val _onPlayList = MutableSharedFlow<Unit>()
    val onPlayList: SharedFlow<Unit> get() = _onPlayList

    private val _diary = MutableSharedFlow<DiaryEntity>()
    val diary: SharedFlow<DiaryEntity> get() = _diary

    fun requestDiary(date: String) = viewModelScope.launch {
        _diary.emit(getDiary(date))
    }

    fun onClickSetting() = viewModelScope.launch {
        _onSetting.emit(Unit)
    }

    fun onClickPlayList() = viewModelScope.launch {
        _onPlayList.emit(Unit)
    }
}
