package com.mashup.kkyuni.playlist.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
): ViewModel() {
}