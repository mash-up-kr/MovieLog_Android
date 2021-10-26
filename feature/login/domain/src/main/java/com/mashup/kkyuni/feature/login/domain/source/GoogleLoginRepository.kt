package com.mashup.kkyuni.feature.login.domain.source

import android.content.Intent
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState

interface GoogleLoginRepository {
    suspend fun googleLogin(): GoogleLoginState

    suspend fun getIdToken(data: Intent): GoogleLoginState
}