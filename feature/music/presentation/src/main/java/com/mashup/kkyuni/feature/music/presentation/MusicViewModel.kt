package com.mashup.kkyuni.feature.music.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.music.domain.GetMusicUseCase
import com.mashup.kkyuni.feature.music.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(private val getMusic: GetMusicUseCase) : ViewModel() {

    private val _videoList = MutableSharedFlow<List<Video>>()
    val videoList: SharedFlow<List<Video>> get() = _videoList

    private val _selectedVideo = MutableSharedFlow<Video>()
    val selectedVideo = _selectedVideo.asSharedFlow()

    fun search(query: String) {
        viewModelScope.launch {
            runCatching {
                getMusic(query).items
            }.onSuccess {
                _videoList.emit(it)
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    fun onVideoClicked(video: Video){
        viewModelScope.launch {
            _selectedVideo.emit(video)
        }
    }
}