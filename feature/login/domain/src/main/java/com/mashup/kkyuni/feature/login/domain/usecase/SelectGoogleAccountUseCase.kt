package com.mashup.kkyuni.feature.login.domain.usecase

import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import javax.inject.Inject

class SelectGoogleAccountUseCase @Inject constructor(
    private val googleLoginRepository: GoogleLoginRepository
) {

    suspend operator fun invoke(): GoogleLoginState = googleLoginRepository.googleLogin()
}