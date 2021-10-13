package com.mashup.kkyuni.feature.login.data.source

import com.mashup.kkyuni.core.preference.SharedPreferenceManager

class LoginPreferenceManager(private val sharedPreferenceManager: SharedPreferenceManager) {

    companion object {
        private const val PREF_KEY_MEMBER_ID = "member_id"
        private const val PREF_KEY_REFRESH_TOKEN = "refresh_token"
        private const val PREF_KEY_SUB = "sub"
        private const val PREF_KEY_TOKEN = "token"
    }

    fun setMemberId(memberId: Int) {
        sharedPreferenceManager.setInt(PREF_KEY_MEMBER_ID, memberId)
    }

    fun getMemberId(): Int {
        return sharedPreferenceManager.getInt(PREF_KEY_MEMBER_ID, -1)
    }

    fun setRefreshToken(refreshToken: String) {
        sharedPreferenceManager.setString(PREF_KEY_REFRESH_TOKEN, refreshToken)
    }

    fun getRefreshToken(): String? {
        return sharedPreferenceManager.getString(PREF_KEY_REFRESH_TOKEN, null)
    }

    fun setSub(sub: String) {
        sharedPreferenceManager.setString(PREF_KEY_SUB, sub)
    }

    fun getSub(): String? {
        return sharedPreferenceManager.getString(PREF_KEY_SUB, null)
    }

    fun setToken(token: String) {
        sharedPreferenceManager.setString(PREF_KEY_TOKEN, token)
    }

    fun getToken(): String? {
        return sharedPreferenceManager.getString(PREF_KEY_TOKEN, null)
    }
}