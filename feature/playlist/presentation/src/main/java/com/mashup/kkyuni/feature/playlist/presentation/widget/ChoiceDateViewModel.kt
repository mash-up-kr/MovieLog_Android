package com.mashup.kkyuni.feature.playlist.presentation.widget

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.playlist.domain.model.ChoiceDate
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.domain.usecase.GetDateListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoiceDateViewModel @Inject constructor(
    private val getDateListUseCase: GetDateListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _currentSelectedPosition = 0

    private val _choiceDates = MutableStateFlow<List<ChoiceDate>>(emptyList())
    val choiceDates = _choiceDates.asStateFlow()

    private val _changedNotiPosition = MutableSharedFlow<Int>()
    val changedNotiPosition = _changedNotiPosition.asSharedFlow()

    private val _choiceDateEvent = MutableSharedFlow<Date>()
    val choiceDateEvent = _choiceDateEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            getDateListUseCase(
                savedStateHandle[ChoiceDateDialogFragment.KEY_YEAR] ?: 2021,
                savedStateHandle[ChoiceDateDialogFragment.KEY_MONTH] ?: 1
            ).collect {
                _choiceDates.emit(it)
            }
        }
    }

    fun selectByPosition(position: Int){
        val list: List<ChoiceDate> = _choiceDates.value

        list.run {
            this[_currentSelectedPosition].isSelected = false
            this[position].isSelected = true
        }

        viewModelScope.launch {
            _choiceDates.emit(list)
        }

        viewModelScope.launch {
            _changedNotiPosition.emit(_currentSelectedPosition)
            _changedNotiPosition.emit(position)
        }
        _currentSelectedPosition = position
    }

    fun onClickedComplete(){
        viewModelScope.launch {
            val date = _choiceDates.value[_currentSelectedPosition].date

            _choiceDateEvent.emit(date)
        }
    }
}