package com.mashup.kkyuni.core.auth

interface AuthPreference {

    suspend fun setToken(userTokens: UserTokens)

    suspend fun getAccessToken(): String?

    suspend fun getRefreshToken(): String?
}
