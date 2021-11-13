package com.mashup.kkyuni.feature.login.domain.source

import android.content.Intent
import android.content.IntentSender
import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo

interface GoogleLoginRepository {
    suspend fun googleLogin(): IntentSender

    suspend fun loginRequest(data: Intent): GoogleLoginAuthInfo
}