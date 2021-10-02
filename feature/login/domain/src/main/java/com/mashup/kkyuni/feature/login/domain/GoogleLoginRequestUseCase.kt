package com.mashup.kkyuni.feature.login.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoogleLoginRequestUseCase @Inject constructor(private val googleGoogleLoginRepository: GoogleLoginRepository) {

    operator fun invoke(idToken: String, onSuccess: () -> Unit, onFailure: (String?) -> Unit, ): Flow<String> =
        googleGoogleLoginRepository.loginRequest(idToken, onSuccess, onFailure)
}