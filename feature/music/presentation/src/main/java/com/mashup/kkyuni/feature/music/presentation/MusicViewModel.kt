package com.mashup.kkyuni.feature.music.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.music.domain.GetMusicUseCase
import com.mashup.kkyuni.feature.music.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(private val getMusicUseCase: GetMusicUseCase) : ViewModel() {

    private lateinit var videos: List<Video>

    fun search(query: String) {
        viewModelScope.launch {
            videos = getMusicUseCase.invoke(query).items
            Log.d("로그", videos.toString())
        }
    }
}