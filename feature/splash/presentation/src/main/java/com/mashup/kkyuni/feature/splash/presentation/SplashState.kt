package com.mashup.kkyuni.feature.splash.presentation

sealed class SplashState {
    object NeedLogin: SplashState()
    object LoginSuccess: SplashState()
    data class Failure(val error: Throwable): SplashState()
}
