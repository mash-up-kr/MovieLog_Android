package com.mashup.kkyuni.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.kkyuni.feature.splash.domain.LoginUseCase
import com.mashup.kkyuni.feature.splash.domain.error.NoTokenException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
): ViewModel() {

    private val _splashState = MutableSharedFlow<SplashState>()
    val splashState: SharedFlow<SplashState> = _splashState

    fun login() {
        viewModelScope.launch {
            loginUseCase()
                .catch { error ->
                    if (error is NoTokenException) {
                        _splashState.emit(SplashState.NeedLogin)
                    } else {
                        _splashState.emit(SplashState.Failure(error))
                    }
                }
                .collect {
                    _splashState.emit(SplashState.LoginSuccess)
                }
        }
    }
}