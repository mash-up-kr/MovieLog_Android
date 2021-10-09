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

    private val _playListFlow = _dateFlow.filter {
        val isValid = isValidDate(it)

        if(!isValid) _backLiveData.value = Unit

        isValid
    }.flatMapLatest { date ->
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

    private fun isValidDate(date: Date): Boolean {
        return !(date.year == -1 || date.month == -1)
    }

    fun updateDate(year: Int, month: Int){
        _dateFlow.value = Date(year, month)
    }

    fun onMusicClicked(item: MusicModel.MusicData){
        _toastLiveData.value = item.linkUrl
    }

    companion object {
        const val TAG = "PlayListViewModel"

        const val KEY_YEAR = "year"
        const val KEY_MONTH = "month"
    }
}