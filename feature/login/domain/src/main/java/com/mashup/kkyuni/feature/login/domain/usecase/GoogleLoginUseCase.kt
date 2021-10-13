package com.mashup.kkyuni.feature.login.domain.usecase

import android.content.IntentSender
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(private val googleGoogleLoginRepository: GoogleLoginRepository) {

    operator fun invoke(onSuccessListener: (IntentSender) -> Unit, onFailureListener: (Exception) -> Unit) {
        googleGoogleLoginRepository.googleLogin(onSuccessListener, onFailureListener)
    }
}