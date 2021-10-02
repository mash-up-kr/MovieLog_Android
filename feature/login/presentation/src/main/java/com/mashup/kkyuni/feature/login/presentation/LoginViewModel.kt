package com.mashup.kkyuni.feature.login.presentation

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.login.domain.GetGoogleIdTokenUseCase
import com.mashup.kkyuni.feature.login.domain.GoogleLoginRequestUseCase
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.domain.GoogleLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val getGoogleIdTokenUseCase: GetGoogleIdTokenUseCase,
    private val googleLoginRequestUseCase: GoogleLoginRequestUseCase
) : ViewModel() {

    private val _googleLoginState = MutableLiveData<GoogleLoginState?>(null)
    val googleLoginState: LiveData<GoogleLoginState?>
        get() = _googleLoginState

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    private var idToken: String? = null

    fun login(onSuccessListener: (IntentSender) -> Unit, onFailureListener: (Exception) -> Unit) {
        googleLoginUseCase(
            onSuccessListener = onSuccessListener,
            onFailureListener = onFailureListener
        )
    }

    fun onActivityResult(data: Intent) {
        getGoogleIdTokenUseCase(data) { idToken, state ->
            this.idToken = idToken
            _googleLoginState.value = state
        }
    }

    fun loginRequest() {
        viewModelScope.launch {
            idToken?.let {
                _isLoading.value = true
                googleLoginRequestUseCase(it,
                    onSuccess = {
                        _isLoading.value = false
                    },
                    onFailure = {
                        _errorMessage.value = it
                    }
                ).collect { response ->
                    //Log.d(javaClass.simpleName, "member Id : ${response.memberId}, refreshToken: ${response.refreshToken}, sub: ${response.sub}, token: ${response.token}")
                }
            }
        }
    }
}