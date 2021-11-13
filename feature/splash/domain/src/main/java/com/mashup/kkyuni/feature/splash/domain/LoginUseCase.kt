package com.mashup.kkyuni.feature.splash.domain

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    operator fun invoke() = flow {
        emit(loginRepository.login())
    }
}