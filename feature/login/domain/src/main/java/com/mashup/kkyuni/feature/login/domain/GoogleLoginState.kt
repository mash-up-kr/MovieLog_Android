package com.mashup.kkyuni.feature.login.domain

sealed class GoogleLoginState<out T> {
    data class Success<out T>(val data: T?): GoogleLoginState<T>()
    data class Fail(val errorMessage: String?) : GoogleLoginState<Nothing>()
}