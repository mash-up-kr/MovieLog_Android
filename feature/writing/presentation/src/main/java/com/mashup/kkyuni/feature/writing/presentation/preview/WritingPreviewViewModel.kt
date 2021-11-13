package com.mashup.kkyuni.feature.writing.presentation.preview

import android.util.Log
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
class WritingPreviewViewModel @Inject constructor(
    private val uploadWritingUseCase: UploadWritingUseCase
) : ViewModel() {
    private val _uplaodingState = MutableStateFlow<UploadState>(UploadState.Idle)
    val uploading = _uplaodingState.asStateFlow()

    private val _completedEvent = MutableSharedFlow<Diary>()
    val completedEvent = _completedEvent.asSharedFlow()

    fun requestUpload(writing: Writing) {
        viewModelScope.launch {
            uploadWritingUseCase(
                UploadWritingUseCase.Params(
                    content = writing.content ?: "",
                    diaryType = writing.type ?: "BLUE1",
                    emotion = writing.emotion?.name ?: "",
                    musicPlayTime = writing.music?.playTime?.toIntOrNull() ?: 0,
                    musicThumbnailImage = writing.music?.thumbnailUrl ?: "",
                    musicTitle = writing.music?.title ?: "",
                    title = writing.title ?: "",
                    youtubeLink = writing.music?.linkUrl ?: "",
                    writingDate = writing.date ?: "",
                    "1",
                    "1"
                )
            ).onStart { _uplaodingState.update { UploadState.Uploading } }
                .onCompletion { _uplaodingState.update { UploadState.Complete } }
                .catch { e ->
                    Log.e(TAG, e.message ?: "")
                }
                .collect {
                    _completedEvent.emit(it)
                }
        }
    }

    companion object {
        private val TAG = this.javaClass.simpleName
    }
}