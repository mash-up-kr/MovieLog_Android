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
import kotlinx.coroutines.launch
import java.util.*

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getPreview: GetPreviewUseCase,
    private val setPreview: SetPreviewUseCase,
    private val getAccessToken: GetAccessTokenUseCase
) : ViewModel() {

    var dateChecked = false

    private val _preview = MutableStateFlow<Boolean>(false)
    val preview: StateFlow<Boolean> get() = _preview

    private val _onSetting = MutableSharedFlow<Unit>()
    val onSetting: SharedFlow<Unit> get() = _onSetting

    private val _onPlayList = MutableSharedFlow<Pair<Int, Int>>()
    val onPlayList = _onPlayList.asSharedFlow()

    private val _currentDate = MutableStateFlow("")
    val currentDate = _currentDate.asStateFlow()

    private val _yearToMonth = MutableStateFlow("")
    val yearToMonth = _yearToMonth.asStateFlow()

    private val _onWriting = MutableSharedFlow<String>()
    val onWriting = _onWriting.asSharedFlow()

    init {
        viewModelScope.launch {
            _preview.emit(getPreview())
        }
    }

    fun updateCurrentDate(date: String) = viewModelScope.launch {
        _currentDate.emit(date)
    }

    fun onClickSetting() = viewModelScope.launch {
        _onSetting.emit(Unit)
    }

    fun onClickPlayList() = viewModelScope.launch {
        if (_currentDate.value.isEmpty()) return@launch

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

    fun onClickWriting() = viewModelScope.launch {
        _onWriting.emit(_currentDate.value)
    }
}
