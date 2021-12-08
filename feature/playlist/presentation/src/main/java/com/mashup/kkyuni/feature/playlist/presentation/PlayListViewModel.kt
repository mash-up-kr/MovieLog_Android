package com.mashup.kkyuni.feature.playlist.presentation

import android.util.Log
import androidx.lifecycle.*
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.feature.playlist.domain.usecase.GetPlayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(
    private val getPlayListUseCase: GetPlayListUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _toastLiveData = MutableLiveData<String>()
    val toastLiveData get() = _toastLiveData

    private val _backLiveData = MutableLiveData<Unit>()
    val backLiveData get() = _backLiveData

    private val _changeDateEvent = MutableSharedFlow<Date>()
    val changeDateEvent = _changeDateEvent.asSharedFlow()

    private val _dateFlow = MutableStateFlow(
        Date(
            year = savedStateHandle[KEY_YEAR] ?: -1,
            month = savedStateHandle[KEY_MONTH] ?: -1
        )
    )
    val dateFlow = _dateFlow.asStateFlow()

    @ExperimentalCoroutinesApi
    private val _playListFlow = _dateFlow.dateFilter().flatMapLatest { date ->
        getPlayListUseCase(
            GetPlayListUseCase.Params(
                year = date.year,
                month = date.month
            )
        ).onStart {
            _loadingFlow.update { true }
        }.onCompletion {
            _loadingFlow.update { false }
        }.catch { error ->
            emit(listOf(MusicModel.EmptyData))
            Log.e(TAG, error.message ?: "")
        }.flowOn(Dispatchers.IO)
    }

    val playList = _playListFlow.asLiveData(viewModelScope.coroutineContext)

    private fun Flow<Date>.dateFilter(): Flow<Date> = transform { value ->
        if (isValidDate(value)) {
            return@transform emit(value)
        } else {
            _backLiveData.value = Unit
        }
    }

    private fun isValidDate(date: Date): Boolean {
        return !(date.year == -1 || date.month == -1)
    }

    fun updateDate(year: Int, month: Int) {
        _dateFlow.update { Date(year, month) }
    }

    fun onMusicClicked(item: MusicModel.MusicData) {
        _toastLiveData.value = item.linkUrl
    }

    fun onBack() {
        _backLiveData.value = Unit
    }

    fun onChangeDate() {
        if (_loadingFlow.value) return

        viewModelScope.launch {
            _changeDateEvent.emit(_dateFlow.value)
        }
    }

    fun getCurrentDate(): String {
        return "${_dateFlow.value.month}-${_dateFlow.value.year}"
    }

    companion object {
        const val TAG = "PlayListViewModel"

        const val KEY_YEAR = "year"
        const val KEY_MONTH = "month"
    }
}