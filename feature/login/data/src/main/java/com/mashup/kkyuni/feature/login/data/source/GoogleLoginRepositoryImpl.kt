package com.mashup.kkyuni.feature.login.data.source

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.mashup.kkyuni.feature.login.data.GoogleLoginRequest
import com.mashup.kkyuni.feature.login.data.toEntity
import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GoogleLoginRepositoryImpl @Inject constructor(
    private val signInClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
    private val googleLoginService: GoogleLoginService
): GoogleLoginRepository {

    override fun googleLogin(onSuccessListener: (IntentSender) -> Unit, onFailureListener: (Exception) -> Unit) {
        signInClient.beginSignIn(signInRequest)
            .addOnSuccessListener {
                onSuccessListener.invoke(it.pendingIntent.intentSender)
            }
            .addOnFailureListener(onFailureListener)
    }

    override fun getIdToken(data: Intent, callback: (idToken: String?, state: GoogleLoginState) -> Unit) {
        var id: String? = null
        var state: GoogleLoginState
        try {
            id = signInClient.getSignInCredentialFromIntent(data).googleIdToken
            state = GoogleLoginState.Success
        } catch (e: ApiException) {
            state = GoogleLoginState.Fail(e.message)
        }
        callback.invoke(id, state)
    }

    override fun loginRequest(idToken: String, onSuccess: () -> Unit, onFailure: (String?) -> Unit): Flow<GoogleLoginAuthInfo> = flow {
        val response = googleLoginService.login(GoogleLoginRequest(idToken))
        emit(response.toEntity())
        onSuccess.invoke()
    }
}