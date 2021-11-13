package com.mashup.kkyuni.feature.login.domain.usecase

import android.content.Intent
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(
    private val googleLoginRepository: GoogleLoginRepository
) {

    suspend operator fun invoke(data: Intent) = flow {
        emit(googleLoginRepository.loginRequest(data))
    }
}