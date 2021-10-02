package com.mashup.kkyuni.feature.login.domain

sealed class GoogleLoginState {
    object Success: GoogleLoginState()
    object Canceled: GoogleLoginState()
    object NetworkError: GoogleLoginState()
    object OtherError: GoogleLoginState()
}