package com.mashup.kkyuni.feature.setting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {

    private val _onBack = MutableSharedFlow<Unit>()
    val onBack: SharedFlow<Unit> get() = _onBack

    private val _onInquiry = MutableSharedFlow<Unit>()
    val onInquiry: SharedFlow<Unit> get() = _onInquiry

    private val _onCreator = MutableSharedFlow<Unit>()
    val onCreator: SharedFlow<Unit> get() = _onCreator

    fun onBackClick() = viewModelScope.launch {
        _onBack.emit(Unit)
    }

    fun onInquiryClick() = viewModelScope.launch {
        _onInquiry.emit(Unit)
    }

    fun onCreatorClick() = viewModelScope.launch {
        _onCreator.emit(Unit)
    }
}
