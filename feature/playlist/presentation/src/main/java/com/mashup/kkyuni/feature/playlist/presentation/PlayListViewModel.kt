package com.mashup.kkyuni.feature.playlist.presentation

import android.util.Log
import androidx.lifecycle.*
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.feature.playlist.domain.usecase.GetPlayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(
    private val getPlayListUseCase: GetPlayListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _toastLiveData = MutableLiveData<String>()
    val toastLiveData get() = _toastLiveData

    private val _backLiveData = MutableLiveData<Unit>()
    val backLiveData get() = _backLiveData

    private val _dateFlow = MutableStateFlow(
        Date(
            year = savedStateHandle[KEY_YEAR] ?: -1,
            month = savedStateHandle[KEY_MONTH] ?: -1
        )
    )
    val dateFlow = _dateFlow.asStateFlow()

    private val _playListFlow = _dateFlow.dateFilter().flatMapLatest { date ->
        getPlayListUseCase(
            GetPlayListUseCase.Params(
                year = date.year,
                month = date.month
            ),
            onStart = { _loadingFlow.update { true } },
            onComplete = { _loadingFlow.update { false } },
            onError = {
                if(!it.isNullOrBlank()) Log.e(TAG, it)
            }
        )
    }

    val playList = _playListFlow.asLiveData(viewModelScope.coroutineContext)

    private fun Flow<Date>.dateFilter(): Flow<Date> = transform { value ->
        if (isValidDate(value)) {
            return@transform emit(value)
        }else {
            _backLiveData.value = Unit
        }
    }

    private fun isValidDate(date: Date): Boolean {
        return !(date.year == -1 || date.month == -1)
    }

    fun updateDate(year: Int, month: Int){
        _dateFlow.update { Date(year, month) }
    }

    fun onMusicClicked(item: MusicModel.MusicData){
        _toastLiveData.value = item.linkUrl
    }

    fun onBack(){
        _backLiveData.value = Unit
    }

    fun onChangeDate(){
        //TODO 테스트 코드 제거
        var date = _dateFlow.value
        if(date.month == 12) date = date.copy(month = 0)
        updateDate(date.year, date.month + 1)
    }

    companion object {
        const val TAG = "PlayListViewModel"

        const val KEY_YEAR = "year"
        const val KEY_MONTH = "month"
    }
}