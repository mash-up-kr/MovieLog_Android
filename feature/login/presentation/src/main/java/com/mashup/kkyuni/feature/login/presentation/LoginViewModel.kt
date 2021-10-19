package com.mashup.kkyuni.feature.login.presentation

import android.content.Intent
import android.content.IntentSender
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

    private val _selectGoogleAccountState = MutableSharedFlow<GoogleLoginState<IntentSender>>()
    val selectGoogleAccountState: SharedFlow<GoogleLoginState<IntentSender>> = _selectGoogleAccountState
    
    private val _googleLoginState = MutableSharedFlow<GoogleLoginState<Unit>>()
    val googleLoginState: MutableSharedFlow<GoogleLoginState<Unit>> = _googleLoginState

    fun selectGoogleAccount() {
        viewModelScope.launch {
            _selectGoogleAccountState.emit(selectGoogleAccountUseCase())
        }
    }

    fun tryGoogleLogin(data: Intent) {
        viewModelScope.launch {
            _googleLoginState.emit(googleLoginUseCase(data))
        }
    }
}