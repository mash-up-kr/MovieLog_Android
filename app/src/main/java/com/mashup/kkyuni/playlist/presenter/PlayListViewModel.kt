package com.mashup.kkyuni.playlist.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.playlist.domain.model.Date
import com.mashup.kkyuni.playlist.domain.model.PlayListState
import com.mashup.kkyuni.playlist.domain.usecase.GetPlayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(
    private val getPlayListUseCase: GetPlayListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _playListState = MutableStateFlow<PlayListState>(
        PlayListState.Progress
    )
    val playListState = _playListState.asStateFlow()

    init {
        fetchPlayList(
            Date(
                year = savedStateHandle[KEY_YEAR] ?: throw Exception("Check your year data"),
                month = savedStateHandle[KEY_MONTH] ?: throw Exception("Check your month data")
            )
        )
    }

    fun fetchPlayList(date: Date){
        viewModelScope.launch {
            getPlayListUseCase(
                GetPlayListUseCase.Params(
                    year = date.year,
                    month = date.month
                )
            ).catch { exception ->
                _playListState.update {
                    PlayListState.Fail(
                    message = exception.message ?: ""
                    )
                }
            }.collect { list ->
                _playListState.update { PlayListState.Success(list) }
            }
        }
    }

    companion object {
        const val KEY_YEAR = "key_year"
        const val KEY_MONTH = "key_month"
    }
}