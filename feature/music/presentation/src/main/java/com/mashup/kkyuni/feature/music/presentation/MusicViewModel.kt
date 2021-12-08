package com.mashup.kkyuni.feature.music.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.music.domain.GetMusicDurationUseCase
import com.mashup.kkyuni.feature.music.domain.GetMusicUseCase
import com.mashup.kkyuni.feature.music.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getMusic: GetMusicUseCase,
    private val getDuration: GetMusicDurationUseCase
) : ViewModel() {

    private val _videoList = MutableSharedFlow<List<Video>>()
    val videoList: SharedFlow<List<Video>> = _videoList

    private val _isSelected = MutableLiveData<Boolean>(false)
    val isSelected: LiveData<Boolean> = _isSelected

    private val _selectedVideo = MutableLiveData<Video>()
    val selectedVideo: LiveData<Video> = _selectedVideo

    private val _completeEvent = MutableSharedFlow<Video>()
    val completeEvent = _completeEvent.asSharedFlow()

    var selectedItemPos = -1

    private val _backEvent = MutableSharedFlow<Unit>()
    val backEvent = _backEvent.asSharedFlow()

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val videos = getMusic(query).items
                videos.forEach {
                    it.duration = getDuration(it.id.videoId)
                }
                _videoList.emit(videos)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setSelectedItem(video: Video) {
        _selectedVideo.value = video
        _isSelected.value = true
    }

    fun clearSelectedData() {
        selectedItemPos = -1
        _isSelected.postValue(false)
    }

    fun onVideoClicked(){
        viewModelScope.launch {
            _selectedVideo.value?.let {
                _completeEvent.emit(it)
            }
        }
    }


    fun onBackClicked() {
        viewModelScope.launch {
            _backEvent.emit(Unit)
        }
    }
}