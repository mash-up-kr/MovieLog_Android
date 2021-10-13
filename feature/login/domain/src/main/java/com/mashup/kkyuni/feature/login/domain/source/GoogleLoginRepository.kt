package com.mashup.kkyuni.feature.login.domain.source

import android.content.Intent
import android.content.IntentSender
import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import kotlinx.coroutines.flow.Flow

interface GoogleLoginRepository {
    fun googleLogin(onSuccessListener: (IntentSender) -> Unit, onFailureListener: (Exception) -> Unit)

    fun getIdToken(data: Intent, callback: (idToken: String?, state: GoogleLoginState) -> Unit)

    fun loginRequest(idToken: String, onSuccess: () -> Unit, onFailure: (String?) -> Unit): Flow<GoogleLoginAuthInfo>
}