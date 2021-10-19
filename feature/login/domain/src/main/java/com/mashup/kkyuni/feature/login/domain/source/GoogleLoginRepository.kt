package com.mashup.kkyuni.feature.login.domain.source

import android.content.Intent
import android.content.IntentSender
import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import kotlinx.coroutines.flow.Flow

interface GoogleLoginRepository {
    suspend fun googleLogin(): GoogleLoginState<IntentSender>

    suspend fun getIdToken(data: Intent): GoogleLoginState<String>
}