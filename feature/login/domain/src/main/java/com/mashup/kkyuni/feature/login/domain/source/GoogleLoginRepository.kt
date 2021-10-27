package com.mashup.kkyuni.feature.login.domain.source

import android.content.Intent
import android.content.IntentSender

interface GoogleLoginRepository {
    suspend fun googleLogin(): IntentSender

    suspend fun getIdToken(data: Intent): String
}