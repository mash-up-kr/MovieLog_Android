package com.mashup.kkyuni.feature.login.domain

sealed class GoogleLoginState {
    data class Success<out T>(val data: T?): GoogleLoginState()
    data class Fail(val errorMessage: String?) : GoogleLoginState()
}