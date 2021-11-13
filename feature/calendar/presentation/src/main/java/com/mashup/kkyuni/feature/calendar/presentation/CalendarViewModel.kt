package com.mashup.kkyuni.feature.calendar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.calendar.domain.GetAccessTokenUseCase
import com.mashup.kkyuni.feature.calendar.domain.GetDiary
import com.mashup.kkyuni.feature.calendar.domain.GetPreviewUseCase
import com.mashup.kkyuni.feature.calendar.domain.SetPreviewUseCase
import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

<<<<<<< HEAD
=======
>>>>>>> 315a7d1 ([#32] play list 진입 부분 작성 및 달 선택 중앙 정렬)
import kotlinx.coroutines.launch
import java.util.*

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getDiary: GetDiary,
    private val getPreview: GetPreviewUseCase,
    private val setPreview: SetPreviewUseCase,
    private val getAccessToken: GetAccessTokenUseCase
) : ViewModel() {

    private val _preview = MutableStateFlow<Boolean>(false)
    val preview: StateFlow<Boolean> get() = _preview

    private val _onSetting = MutableSharedFlow<Unit>()
    val onSetting: SharedFlow<Unit> get() = _onSetting

    private val _onPlayList = MutableSharedFlow<Pair<Int, Int>>()
    val onPlayList= _onPlayList.asSharedFlow()

    private val _diary = MutableSharedFlow<DiaryEntity>()
    val diary: SharedFlow<DiaryEntity> get() = _diary

    private val _currentDate = MutableStateFlow("")
    val currentDate = _currentDate.asStateFlow()

    init {
        viewModelScope.launch {
            _preview.emit(getPreview())
        }
    }

    fun updateCurrentDate(date: String) = viewModelScope.launch{
        _currentDate.emit(date)
    }

    fun requestDiary(date: String) = viewModelScope.launch {
        _diary.emit(getDiary(date))
    }

    fun onClickSetting() = viewModelScope.launch {
        _onSetting.emit(Unit)
    }

    fun onClickPlayList() = viewModelScope.launch {
        if(_currentDate.value.isEmpty()) return@launch

        val (year, month) = _currentDate.value.split("-").map { it.toInt() }
        _onPlayList.emit(year to month)
    }

    fun onClickPreviewBackground() = viewModelScope.launch {
        setPreview(true)
        _preview.emit(true)
    }

    fun getUserAccessToken(): String? {
        return getAccessToken()
    }
}
