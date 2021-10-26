package com.mashup.kkyuni.feature.login.domain.usecase

import android.content.Intent
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoDataSource
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoRepository
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(
    private val googleGoogleLoginRepository: GoogleLoginRepository,
    private val googleLoginAuthInfoRepository: GoogleLoginAuthInfoRepository,
    private val googleLoginAuthInfoDataSource: GoogleLoginAuthInfoDataSource
) {

    suspend operator fun invoke(data: Intent): GoogleLoginState {
        return when (val state = googleGoogleLoginRepository.getIdToken(data)) {
            is GoogleLoginState.Success<*> -> {
                if (state.data == null || state.data !is String) {
                    GoogleLoginState.Fail("idToken is wrong")
                } else {
                    val googleLoginAuthInfo = googleLoginAuthInfoRepository.loginRequest(state.data)
                    googleLoginAuthInfoDataSource.setGoogleLoginAuthInfo(googleLoginAuthInfo)
                    GoogleLoginState.Success(state.data)
                }
            }
            else -> state
        }
    }
}