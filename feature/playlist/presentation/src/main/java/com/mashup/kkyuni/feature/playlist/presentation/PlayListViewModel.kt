package com.mashup.kkyuni.feature.playlist.presentation

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

    private val _toastFlow = MutableStateFlow("")
    val toastFlow = _toastFlow.asStateFlow()

    private val _backEventFlow = MutableSharedFlow<Unit>()
    val backEventFlow = _backEventFlow.asSharedFlow()

    private val _dateFlow = MutableStateFlow(
        Date(
            year = savedStateHandle[KEY_YEAR] ?: -1,
            month = savedStateHandle[KEY_MONTH] ?: -1
        )
    )

    private val _playListFlow = _dateFlow.flatMapLatest { date ->
        getPlayListUseCase(
            GetPlayListUseCase.Params(
                year = date.year,
                month = date.month
            ),
            onStart = { _loadingFlow.update { true } },
            onComplete = { _loadingFlow.update { false } },
            onError = { _toastFlow.update { it } }
        )
    }

    val playList = _playListFlow.asLiveData(viewModelScope.coroutineContext)

    fun updateDate(year: Int, month: Int){
        _dateFlow.update { Date(year, month) }
    }

    companion object {
        const val KEY_YEAR = "year"
        const val KEY_MONTH = "month"
    }
}