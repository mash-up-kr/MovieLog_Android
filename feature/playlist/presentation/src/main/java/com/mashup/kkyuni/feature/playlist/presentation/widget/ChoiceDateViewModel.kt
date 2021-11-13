package com.mashup.kkyuni.feature.playlist.presentation.widget

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.playlist.domain.model.ChoiceDate
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.domain.usecase.GetAfterDateListUseCase
import com.mashup.kkyuni.feature.playlist.domain.usecase.GetDateListUseCase
import com.mashup.kkyuni.feature.playlist.domain.usecase.GetPreviousDateListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoiceDateViewModel @Inject constructor(
    private val getDateListUseCase: GetDateListUseCase,
    private val getPreviousDateListUseCase: GetPreviousDateListUseCase,
    private val getAfterDateListUseCase: GetAfterDateListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _currentSelectedPosition = 0

    private val _choiceDates = MutableStateFlow<List<ChoiceDate>>(emptyList())
    val choiceDates = _choiceDates.asStateFlow()

    private val _changedNotifyEvent = MutableSharedFlow<Unit>()
    val changedNotifyEvent = _changedNotifyEvent.asSharedFlow()

    private val _choiceDateEvent = MutableSharedFlow<Date>()
    val choiceDateEvent = _choiceDateEvent.asSharedFlow()

    private val _scrollToPosition = MutableStateFlow(RecyclerView.NO_POSITION)
    val scrollToPosition get() = _scrollToPosition

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getDateListUseCase(
                savedStateHandle[ChoiceDateDialogFragment.KEY_YEAR] ?: 2021,
                savedStateHandle[ChoiceDateDialogFragment.KEY_MONTH] ?: 1
            ).collect {
                _choiceDates.emit(it)

                _scrollToPosition.emit((it.lastIndex / 2) + 2)
            }
        }
    }

    fun selectByPosition(position: Int){
        val list: List<ChoiceDate> = _choiceDates.value

        list.run {
            forEach {
                it.isSelected = false
            }
            this[position].isSelected = true
        }

        viewModelScope.launch {
            _choiceDates.emit(list)
            _changedNotifyEvent.emit(Unit)
        }

        _currentSelectedPosition = position
    }

    fun onClickedComplete(){
        viewModelScope.launch {
            val date = _choiceDates.value[_currentSelectedPosition].date

            _choiceDateEvent.emit(date)
        }
    }

    fun addPreviousYear(){
        viewModelScope.launch {
            if(_isLoading.value) return@launch

            val date = _choiceDates.value.first().date

            getPreviousDateListUseCase(date.year, date.month)
                .onStart { _isLoading.emit(true) }
                .onCompletion { _isLoading.emit(false) }
                .catch { e ->
                    Log.e(TAG, e.message ?: "")
                }
                .collect {
                    val list = it +_choiceDates.value
                   _choiceDates.emit(list)
                }
        }
    }

    fun addAfterYear(){
        viewModelScope.launch {
            if(_isLoading.value) return@launch

            val date = _choiceDates.value.last().date
            getAfterDateListUseCase(date.year, date.month)
                .onStart { _isLoading.emit(true) }
                .onCompletion { _isLoading.emit(false) }
                .catch { e ->
                    Log.e(TAG, e.message ?: "")
                }
                .collect {
                    val list = _choiceDates.value + it
                    _choiceDates.emit(list)
                }
        }
    }

    companion object {
        const val TAG = "ChoiceDateViewModel"
    }
}