package com.mashup.kkyuni.feature.login.domain.usecase

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoogleLoginRequestUseCase @Inject constructor(private val googleGoogleLoginRepository: GoogleLoginRepository) {

    operator fun invoke(idToken: String, onSuccess: () -> Unit, onFailure: (String?) -> Unit, ): Flow<GoogleLoginAuthInfo> =
        googleGoogleLoginRepository.loginRequest(idToken, onSuccess, onFailure)
}