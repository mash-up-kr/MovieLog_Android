package com.mashup.kkyuni.feature.splash.data

import com.mashup.kkyuni.core.CoroutineDispatcherModule.DispatcherIO
import com.mashup.kkyuni.core.auth.AuthPreference
import com.mashup.kkyuni.feature.splash.domain.LoginRepository
import com.mashup.kkyuni.feature.splash.domain.error.NoTokenException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val authPreference: AuthPreference,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): LoginRepository {

    override suspend fun login(): com.mashup.kkyuni.feature.splash.domain.AuthInfo = withContext(ioDispatcher) {
        val token = authPreference.getAccessToken() ?: throw NoTokenException()
        try {
            loginService.login("Bearer $token").toEntity()
        } catch (e: Exception) {
            authPreference.setToken(null)
            throw e
        }
    }
}