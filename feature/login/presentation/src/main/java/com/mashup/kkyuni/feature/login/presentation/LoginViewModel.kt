package com.mashup.kkyuni.feature.login.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.domain.usecase.GoogleLoginUseCase
import com.mashup.kkyuni.feature.login.domain.usecase.SelectGoogleAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val selectGoogleAccountUseCase: SelectGoogleAccountUseCase,
    private val googleLoginUseCase: GoogleLoginUseCase
) : ViewModel() {

    private val _googleLoginState = MutableSharedFlow<GoogleLoginState>()
    val googleLoginState: SharedFlow<GoogleLoginState> = _googleLoginState

    fun selectGoogleAccount() {
        viewModelScope.launch {
            _googleLoginState.emit(selectGoogleAccountUseCase())
        }
    }

    fun tryGoogleLogin(data: Intent) {
        viewModelScope.launch {
            _googleLoginState.emit(googleLoginUseCase(data))
        }
    }
}