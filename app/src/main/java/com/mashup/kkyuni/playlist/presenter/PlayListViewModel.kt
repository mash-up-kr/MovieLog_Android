package com.mashup.kkyuni.playlist.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mashup.kkyuni.playlist.domain.usecase.GetPlayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(
    private val getPlayListUseCase: GetPlayListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

}