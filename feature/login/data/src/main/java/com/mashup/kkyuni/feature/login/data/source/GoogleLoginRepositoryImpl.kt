package com.mashup.kkyuni.feature.login.data.source

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.mashup.kkyuni.core.CoroutineDispatcherModule.DispatcherIO
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
) : GoogleLoginRepository {

    override suspend fun googleLogin(): IntentSender {
        return withContext(ioDispatcher) {
            awaitGoogleLoginForIntentSender()
        }
    }

    override suspend fun getIdToken(data: Intent): String {
        return withContext(ioDispatcher) {
            signInClient.getSignInCredentialFromIntent(data).googleIdToken
                ?: throw IllegalStateException("idToken is null")
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