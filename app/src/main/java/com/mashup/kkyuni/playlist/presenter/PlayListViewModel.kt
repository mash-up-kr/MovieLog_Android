package com.mashup.kkyuni.playlist.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.playlist.domain.model.Date
import com.mashup.kkyuni.playlist.domain.model.PlayList
import com.mashup.kkyuni.playlist.domain.usecase.GetPlayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(
    private val getPlayListUseCase: GetPlayListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _dateFlow = MutableStateFlow<Date>(
        Date(
            year = savedStateHandle[KEY_YEAR] ?: -1,
            month = savedStateHandle[KEY_MONTH] ?: -1
        )
    )

    private val _playListFlow = MutableStateFlow<List<PlayList>>(
        listOf(PlayList.EmptyData)
    )
    val playListFlow = _playListFlow.asStateFlow()

    private fun fetchPlayList() {
        viewModelScope.launch {
            runCatching {
                _dateFlow.collect {
                    getPlayListUseCase(
                        GetPlayListUseCase.Params(
                            year = it.year,
                            month = it.month
                        )
                    )
                }
            }.onSuccess {
                _playListFlow.update { it }
            }.onFailure {
                // TODO error 처리
            }
        }
    }

    private fun updateDateFlow(year: Int, month: Int){
        _dateFlow.update { Date(year, month) }
    }

    companion object {
        const val KEY_YEAR = "key_year"
        const val KEY_MONTH = "key_month"
    }
}