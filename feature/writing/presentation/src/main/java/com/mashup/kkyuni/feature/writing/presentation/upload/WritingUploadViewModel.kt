package com.mashup.kkyuni.feature.writing.presentation.upload

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.writing.domain.model.Diary
import com.mashup.kkyuni.feature.writing.domain.model.UploadState
import com.mashup.kkyuni.feature.writing.domain.model.Writing
import com.mashup.kkyuni.feature.writing.domain.usecase.UploadWritingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingUploadViewModel @Inject constructor(
    private val uploadWritingUseCase: UploadWritingUseCase
): ViewModel() {
    private val _uplaodingState = MutableStateFlow<UploadState>(UploadState.Uploading)
    val uploading = _uplaodingState.asStateFlow()

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private val _completedEvent = MutableSharedFlow<Diary>()
    val completedEvent = _completedEvent.asSharedFlow()

     fun setDateString(year: String, month: String, day: String){
        _date.value = "$year.$month.$day"
    }

    fun requestUpload(writing: Writing){
        viewModelScope.launch {
            runCatching {
                uploadWritingUseCase(
                    UploadWritingUseCase.Params(
                        content = writing.content ?: "",
                        diaryType = writing.type ?: "",
                        emotion = writing.emotion?.name ?: "",
                        musicPlayTime = writing.music?.playTime?.toIntOrNull() ?: 0,
                        musicThumbnailImage = writing.music?.thumbnailUrl ?: "",
                        musicTitle = writing.music?.title ?: "",
                        title = writing.title ?: "",
                        youtubeLink = writing.music?.linkUrl ?: "",
                        "",
                        ""
                    )
                ).onStart { _uplaodingState.update { UploadState.Uploading } }
                 .onCompletion { _uplaodingState.update { UploadState.Complete } }
            }.onSuccess {
                it.collect { diary ->
                    _completedEvent.emit(diary)
                }
            }.onFailure {
                Log.e(TAG, it.message ?: "")
            }
        }
    }

    companion object {
        private val TAG = this.javaClass.simpleName
    }
}