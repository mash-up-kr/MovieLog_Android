package com.mashup.kkyuni.feature.login.data.source

import com.mashup.kkyuni.core.preference.SharedPreferenceManager
import com.mashup.kkyuni.feature.login.domain.source.LoginPreferenceManager
import javax.inject.Inject

class LoginPreferenceManagerImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager
) : LoginPreferenceManager {

    companion object {
        private const val PREF_KEY_MEMBER_ID = "member_id"
        private const val PREF_KEY_REFRESH_TOKEN = "refresh_token"
        private const val PREF_KEY_TOKEN = "token"
    }

    override fun setMemberId(memberId: Int) {
        sharedPreferenceManager.setInt(PREF_KEY_MEMBER_ID, memberId)
    }

    override fun getMemberId(): Int {
        return sharedPreferenceManager.getInt(PREF_KEY_MEMBER_ID, -1)
    }

    override fun setRefreshToken(refreshToken: String) {
        sharedPreferenceManager.setString(PREF_KEY_REFRESH_TOKEN, refreshToken)
    }

    override fun getRefreshToken(): String? {
        return sharedPreferenceManager.getString(PREF_KEY_REFRESH_TOKEN, null)
    }

    override fun setToken(token: String) {
        sharedPreferenceManager.setString(PREF_KEY_TOKEN, token)
    }

    override fun getToken(): String? {
        return sharedPreferenceManager.getString(PREF_KEY_TOKEN, null)
    }
}