package com.mashup.kkyuni.core.auth

import com.mashup.kkyuni.core.preference.SharedPreferenceManager
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager
) : AuthPreference {

    companion object {
        private const val PREF_TOKEN_KEY = "token_key"
    }

    private var cachedUserTokens: UserTokens? = null
    private var cacheIsDirty = true

    override suspend fun setToken(userTokens: UserTokens) {
        sharedPreferenceManager.setString(PREF_TOKEN_KEY, userTokens.toJason())
        cachedUserTokens = userTokens
        cacheIsDirty = false
    }

    override suspend fun getAccessToken(): String? {
        return getCachedUserTokens()?.accessToken
    }

    override suspend fun getRefreshToken(): String? {
        return getCachedUserTokens()?.refreshToken
    }

    private fun getCachedUserTokens(): UserTokens? {
        if (cacheIsDirty) {
            val json = sharedPreferenceManager.getString(PREF_TOKEN_KEY) ?: return null
            cachedUserTokens = UserTokens.toObject(json)
            cacheIsDirty = false
        }
        return cachedUserTokens
    }
}
