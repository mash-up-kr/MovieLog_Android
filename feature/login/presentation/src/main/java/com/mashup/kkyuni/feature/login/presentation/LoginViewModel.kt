package com.mashup.kkyuni.feature.login.presentation

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.login.domain.usecase.GoogleLoginUseCase
import com.mashup.kkyuni.feature.login.domain.usecase.SelectGoogleAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val selectGoogleAccountUseCase: SelectGoogleAccountUseCase,
    private val googleLoginUseCase: GoogleLoginUseCase
) : ViewModel() {

    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow: StateFlow<Boolean> = _loadingFlow

    private val _toastLiveData = MutableLiveData<String>()
    val toastLiveData: LiveData<String> = _toastLiveData

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    private val _selectAccountIntentSender = MutableLiveData<IntentSender>()
    val selectAccountIntentSender: LiveData<IntentSender> = _selectAccountIntentSender

    fun selectGoogleAccount() {
        viewModelScope.launch {
            selectGoogleAccountUseCase()
                .onStart {
                    _loadingFlow.update { true }
                }.onCompletion {
                    _loadingFlow.update { false }
                }.catch { error ->
                    _toastLiveData.value = error.message
                }.collect {
                    _selectAccountIntentSender.value = it
                }
        }
    }

    fun tryGoogleLogin(data: Intent) {
        viewModelScope.launch {
            googleLoginUseCase(data)
                .onStart {
                    _loadingFlow.update { true }
                }.onCompletion {
                    _loadingFlow.update { false }
                }.catch { error ->
                    _toastLiveData.value = error.message
                }.collect {
                    _loginSuccess.emit(true)
                }
        }
    }
}