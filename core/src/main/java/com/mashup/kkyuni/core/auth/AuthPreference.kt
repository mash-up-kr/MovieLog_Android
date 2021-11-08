package com.mashup.kkyuni.core.auth

interface AuthPreference {

    fun setToken(userTokens: UserTokens?)

    fun getAccessToken(): String?

    fun getRefreshToken(): String?
}
