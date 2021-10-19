package com.mashup.kkyuni.feature.login.domain.source

interface LoginPreferenceManager {

    fun setMemberId(memberId: Int)
    fun getMemberId(): Int

    fun setRefreshToken(refreshToken: String)
    fun getRefreshToken(): String?

    fun setToken(token: String)
    fun getToken(): String?
}
