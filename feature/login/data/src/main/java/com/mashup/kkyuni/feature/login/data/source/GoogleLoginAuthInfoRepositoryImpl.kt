package com.mashup.kkyuni.feature.login.data.source

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoRepository
import javax.inject.Inject

class GoogleLoginAuthInfoRepositoryImpl @Inject constructor(
    private val loginPreferenceManager: LoginPreferenceManager
) : GoogleLoginAuthInfoRepository {

    override fun setGoogleLoginAuthInfo(googleLoginAuthInfo: GoogleLoginAuthInfo) {
        with(loginPreferenceManager) {
            setMemberId(googleLoginAuthInfo.memberId)
            setRefreshToken(googleLoginAuthInfo.refreshToken)
            setSub(googleLoginAuthInfo.sub)
            setToken(googleLoginAuthInfo.token)
        }
    }

    override fun getGoogleLoginAuthInfo(): GoogleLoginAuthInfo? {
        val memberId = loginPreferenceManager.getMemberId()
        if (memberId < 0) return null
        val refreshToken = loginPreferenceManager.getRefreshToken() ?: return null
        val sub = loginPreferenceManager.getSub() ?: return null
        val token = loginPreferenceManager.getToken() ?: return null
        return GoogleLoginAuthInfo(memberId, refreshToken, sub, token)
    }
}