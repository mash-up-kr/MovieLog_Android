package com.mashup.kkyuni.feature.calendar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.calendar.domain.*
import com.mashup.kkyuni.feature.calendar.domain.model.CalendarDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class KobaCalendarViewModel @Inject constructor(
	private val getCalendarDateUseCase: GetCalendarDateUseCase,
	private val getPreviousDateUseCase: GetPreviousDateUseCase,
	private val getAfterDateUseCase: GetAfterDateUseCase,
	private val getPreview: GetPreviewUseCase,
	private val setPreview: SetPreviewUseCase,
	private val getAccessToken: GetAccessTokenUseCase
): ViewModel() {
	private val _isPreviewVisible = MutableStateFlow(false)
	val isPreviewVisible: StateFlow<Boolean> get() = _isPreviewVisible

	private val _onSetting = MutableSharedFlow<Unit>()
	val onSetting: SharedFlow<Unit> get() = _onSetting

	private val _onPlayList = MutableSharedFlow<Pair<Int, Int>>()
	val onPlayList = _onPlayList.asSharedFlow()

	private val _currentDate = MutableStateFlow(
		Calendar.getInstance().run {
			CalendarDate(
				get(Calendar.YEAR), get(Calendar.MONTH) + 1, get(Calendar.DATE)
			)
		}
	)
	val currentDate = _currentDate.asStateFlow()

	private val _onWriting = MutableSharedFlow<String>()
	val onWriting = _onWriting.asSharedFlow()

	private val _calendarDateList = MutableStateFlow(emptyList<CalendarDate>())
	val calendarDateList = _calendarDateList.asStateFlow()

	private val _isLoading = MutableStateFlow(false)
	val isLoading = _isLoading.asStateFlow()

	private val _scrollToPosition = MutableStateFlow(RecyclerView.NO_POSITION)
	val scrollToPosition = _scrollToPosition.asStateFlow()

	init {
		fetchCalendarDateList()

		viewModelScope.launch {
			_isPreviewVisible.emit(!getPreview())
		}
	}

	fun fetchCalendarDateList(){
		val date = _currentDate.value

		viewModelScope.launch {
			runCatching {
				getCalendarDateUseCase(date.year, date.month, date.day)
					.single()
			}.onSuccess {
				_calendarDateList.emit(it)
				_scrollToPosition.emit(it.size / 2 + 2)
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
		_isPreviewVisible.emit(false)
	}

	fun onClickWriting() = viewModelScope.launch {
		val date = _currentDate.value
		_onWriting.emit("${date.year}-${date.month}-${date.day}")
	}

	fun updateCurrentCalendarDate(calendarDate: CalendarDate){
		viewModelScope.launch {
			_currentDate.value = calendarDate
		}
	}

	fun addPreviousDateList(){
		if(_isLoading.value) return

		val date = _calendarDateList.value.first()

		viewModelScope.launch {
			getPreviousDateUseCase(
				date.year, date.month, date.day
			).onStart { _isLoading.emit(true) }
				.onCompletion { _isLoading.emit(false) }
				.collect {
					val list = it + _calendarDateList.value
					_calendarDateList.value = list
				}
		}
	}

	fun addAfterDateList(){
		if(_isLoading.value) return

		val date = _calendarDateList.value.last()

		viewModelScope.launch {
			getAfterDateUseCase(
				date.year, date.month, date.day
			).onStart { _isLoading.emit(true) }
				.onCompletion { _isLoading.emit(false) }
				.collect {
					val list = _calendarDateList.value + it
					_calendarDateList.value = list
				}
		}
	}

	fun getUserAccessToken(): String? {
		return getAccessToken()
	}
}