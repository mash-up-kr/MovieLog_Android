package com.mashup.kkyuni.feature.playlist.presentation.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.playlist.domain.model.ChoiceDate
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoiceDateViewModel @Inject constructor(): ViewModel() {

    private var _currentSelectedPosition = 0

    private val _choiceDates = MutableStateFlow<List<ChoiceDate>>(emptyList())
    val choiceDates = _choiceDates.asStateFlow()

    private val _changedNotiPosition = MutableSharedFlow<Int>()
    val changedNotiPosition = _changedNotiPosition.asSharedFlow()

    init {
        viewModelScope.launch {
            _choiceDates.value = listOf(
                    ChoiceDate(Date(2021, 1)),
                    ChoiceDate(Date(2021, 2)),
                    ChoiceDate(Date(2021, 3)),
                    ChoiceDate(Date(2021, 4)),
                    ChoiceDate(Date(2021, 5)),
                    ChoiceDate(Date(2021, 6)),
                    ChoiceDate(Date(2021, 7)),
                    ChoiceDate(Date(2021, 8)),
                    ChoiceDate(Date(2021, 9)),
                    ChoiceDate(Date(2021, 10)),
                    ChoiceDate(Date(2021, 11)),
                    ChoiceDate(Date(2021, 12))
                )
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
}