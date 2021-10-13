package com.mashup.kkyuni.feature.login.domain

sealed class GoogleLoginState {
    object Success: GoogleLoginState()
    data class Fail(val errorMessage: String?) : GoogleLoginState()
}