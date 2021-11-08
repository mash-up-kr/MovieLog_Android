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

    override fun setToken(userTokens: UserTokens?) {
        val json = userTokens?.toJson()
        sharedPreferenceManager.setString(PREF_TOKEN_KEY, json)
        cachedUserTokens = userTokens
        cacheIsDirty = false
    }

    override fun getAccessToken(): String? {
        return getCachedUserTokens()?.accessToken
    }

    override fun getRefreshToken(): String? {
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
