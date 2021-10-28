package com.mashup.kkyuni.feature.writing.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mashup.kkyuni.feature.writing.domain.model.Writing
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WritingViewModel @Inject constructor(): ViewModel() {
    private val _writing = MutableLiveData<Writing>()
    val writing: LiveData<Writing> get() = _writing


}