package com.mashup.kkyuni.feature.login.data.source

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.mashup.kkyuni.core.CoroutineDispatcherModule.DispatcherIO
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GoogleLoginRepositoryImpl @Inject constructor(
    private val signInClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): GoogleLoginRepository {

    override suspend fun googleLogin(): GoogleLoginState {
        return withContext(ioDispatcher) {
            try {
                GoogleLoginState.Success(awaitGoogleLoginForIntentSender())
            } catch (e: Exception) {
                GoogleLoginState.Fail(e.message)
            }
        }
    }

    override suspend fun getIdToken(data: Intent): GoogleLoginState {
        return withContext(ioDispatcher) {
            try {
                val idToken = signInClient.getSignInCredentialFromIntent(data).googleIdToken
                if (idToken != null) {
                    GoogleLoginState.Success(idToken)
                } else {
                    GoogleLoginState.Fail("idToken is null")
                }
            } catch (e: ApiException) {
                GoogleLoginState.Fail(e.message)
            }
        }
    }

    private suspend fun awaitGoogleLoginForIntentSender(): IntentSender = suspendCoroutine { cont ->
        signInClient.beginSignIn(signInRequest)
            .addOnSuccessListener {
                cont.resume(it.pendingIntent.intentSender)
            }
            .addOnFailureListener {
                cont.resumeWithException(it)
            }
    }
}