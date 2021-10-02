package com.mashup.kkyuni.feature.login.domain

import android.content.Intent
import android.content.IntentSender
import kotlinx.coroutines.flow.Flow

interface GoogleLoginRepository {
    fun googleLogin(onSuccessListener: (IntentSender) -> Unit, onFailureListener: (Exception) -> Unit)

    fun getIdToken(data: Intent, callback: (idToken: String?, state: GoogleLoginState) -> Unit)

    fun loginRequest(idToken: String, onSuccess: () -> Unit, onFailure: (String?) -> Unit): Flow<String>
}