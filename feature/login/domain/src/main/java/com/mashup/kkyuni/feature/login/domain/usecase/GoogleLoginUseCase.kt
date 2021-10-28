package com.mashup.kkyuni.feature.login.domain.usecase

import android.content.Intent
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoDataSource
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoRepository
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(
    private val googleGoogleLoginRepository: GoogleLoginRepository,
    private val googleLoginAuthInfoRepository: GoogleLoginAuthInfoRepository,
    private val googleLoginAuthInfoDataSource: GoogleLoginAuthInfoDataSource
) {

    suspend operator fun invoke(data: Intent) = flow {
        val state = googleGoogleLoginRepository.getIdToken(data)
        val googleLoginAuthInfo = googleLoginAuthInfoRepository.loginRequest(state)
        googleLoginAuthInfoDataSource.setGoogleLoginAuthInfo(googleLoginAuthInfo)
        emit(googleLoginAuthInfo)
    }
}