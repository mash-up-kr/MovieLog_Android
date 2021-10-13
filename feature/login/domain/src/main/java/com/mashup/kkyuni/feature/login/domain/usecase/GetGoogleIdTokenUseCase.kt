package com.mashup.kkyuni.feature.login.domain.usecase

import android.content.Intent
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import javax.inject.Inject

class GetGoogleIdTokenUseCase @Inject constructor(private val googleGoogleLoginRepository: GoogleLoginRepository) {

    operator fun invoke(data: Intent, callback: (idToken: String?, state: GoogleLoginState) -> Unit) {
        return googleGoogleLoginRepository.getIdToken(data, callback)
    }
}