package com.mashup.kkyuni.feature.login.domain.source

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo

interface LoginPreferenceManager {

    fun setMemberId(memberId: Int)
    fun getMemberId(): Int

    fun setRefreshToken(refreshToken: String)
    fun getRefreshToken(): String?

    fun setToken(token: String)
    fun getToken(): String?

    fun setGoogleLoginAuthInfo(googleLoginAuthInfo: GoogleLoginAuthInfo)
    fun getGoogleLoginAuthInfo(): GoogleLoginAuthInfo?
}
