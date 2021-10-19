package com.mashup.kkyuni.feature.login.domain.source

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo

interface GoogleLoginAuthInfoDataSource {

    suspend fun setGoogleLoginAuthInfo(googleLoginAuthInfo: GoogleLoginAuthInfo)

    suspend fun getGoogleLoginAuthInfo(): GoogleLoginAuthInfo?
}