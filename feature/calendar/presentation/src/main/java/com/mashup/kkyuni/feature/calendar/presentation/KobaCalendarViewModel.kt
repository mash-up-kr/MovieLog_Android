package com.mashup.kkyuni.feature.calendar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.calendar.domain.GetAccessTokenUseCase
import com.mashup.kkyuni.feature.calendar.domain.GetCalendarDateUseCase
import com.mashup.kkyuni.feature.calendar.domain.GetPreviewUseCase
import com.mashup.kkyuni.feature.calendar.domain.SetPreviewUseCase
import com.mashup.kkyuni.feature.calendar.domain.model.CalendarDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class KobaCalendarViewModel @Inject constructor(
	private val getCalendarDateUseCase: GetCalendarDateUseCase,
	private val getPreview: GetPreviewUseCase,
	private val setPreview: SetPreviewUseCase,
	private val getAccessToken: GetAccessTokenUseCase
): ViewModel() {
	private val _preview = MutableStateFlow(false)
	val preview: StateFlow<Boolean> get() = _preview

	private val _onSetting = MutableSharedFlow<Unit>()
	val onSetting: SharedFlow<Unit> get() = _onSetting

	private val _onPlayList = MutableSharedFlow<Pair<Int, Int>>()
	val onPlayList = _onPlayList.asSharedFlow()

	private val _currentDate = MutableStateFlow(
		Calendar.getInstance().run {
			CalendarDate(
				get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DATE)
			)
		}
	)
	val currentDate = _currentDate.asStateFlow()

	private val _onWriting = MutableSharedFlow<String>()
	val onWriting = _onWriting.asSharedFlow()

	private val _calendarDateList = MutableStateFlow(emptyList<CalendarDate>())
	val calendarDateList = _calendarDateList.asStateFlow()

	init {
		fetchCalendarDateList()
	}

	private fun fetchCalendarDateList(){
		val date = _currentDate.value

		viewModelScope.launch {
			runCatching {
				getCalendarDateUseCase(date.year, date.month, date.day)
					.single()
			}.onSuccess {
				_calendarDateList.emit(it)
			}
		}
	}

	fun onClickSetting() = viewModelScope.launch {
		_onSetting.emit(Unit)
	}

	fun onClickPlayList() = viewModelScope.launch {
		val date = _currentDate.value
		_onPlayList.emit(date.year to date.month)
	}

	fun onClickPreviewBackground() = viewModelScope.launch {
		setPreview(true)
		_preview.emit(true)
	}

	fun onClickWriting() = viewModelScope.launch {
		val date = _currentDate.value
		_onWriting.emit("${date.year}-${date.month}-${date.day}")
	}
}