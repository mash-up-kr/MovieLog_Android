package com.mashup.kkyuni.feature.login.domain

import android.content.IntentSender
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(private val googleGoogleLoginRepository: GoogleLoginRepository) {

    operator fun invoke(onSuccessListener: (IntentSender) -> Unit, onFailureListener: (Exception) -> Unit) {
        googleGoogleLoginRepository.googleLogin(onSuccessListener, onFailureListener)
    }
}