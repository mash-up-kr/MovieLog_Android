package com.mashup.kkyuni.feature.login.domain.source

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo

interface GoogleLoginAuthInfoRepository {
    suspend fun loginRequest(idToken: String): GoogleLoginAuthInfo
}