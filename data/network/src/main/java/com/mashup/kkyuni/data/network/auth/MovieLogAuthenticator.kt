package com.mashup.kkyuni.data.network.auth

import android.util.Log
import com.mashup.kkyuni.core.auth.AuthPreference
import kotlinx.coroutines.*
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class MovieLogAuthenticator @Inject constructor(
    private val authService: AuthService,
    private val authPreference: AuthPreference
): Authenticator {

    companion object {
        private const val TAG = "MovieLogAuthenticator"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401) {
            val refreshToken = authPreference.getRefreshToken()
            if (refreshToken == null) {
                Log.d(TAG, "refreshToken is null.")
                return null
            }
            val tokenJob: Deferred<String?> = GlobalScope.async(Dispatchers.IO) {
                Log.d(TAG, "refreshToken : $refreshToken")
                val userTokens = authService.refreshToken("Bearer $refreshToken")
                Log.d(TAG, "userToken from server : $userTokens")
                authPreference.setToken(userTokens)
                userTokens.accessToken
            }

            val token = runBlocking { tokenJob.await() }
            if (token != null) {
                return response.request
                    .newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                //Todo: 로그인 화면으로 보내야 함
                authPreference.setToken(null)
                throw IllegalStateException("All tokens are expired")
            }
        }
        return null
    }
}