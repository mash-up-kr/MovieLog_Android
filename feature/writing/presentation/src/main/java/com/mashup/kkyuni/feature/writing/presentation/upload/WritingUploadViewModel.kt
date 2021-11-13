package com.mashup.kkyuni.feature.writing.presentation.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WritingUploadViewModel @Inject constructor(): ViewModel() {
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    fun setDateString(year: String, month: String, day: String) {
        _date.value = "$year.$month.$day"
    }
}