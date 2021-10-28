package com.mashup.kkyuni.feature.login.data.source

import com.mashup.kkyuni.feature.login.data.GoogleLoginRequest
import com.mashup.kkyuni.feature.login.data.toEntity
import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.mashup.kkyuni.core.CoroutineDispatcherModule.DispatcherIO

class GoogleLoginAuthInfoRepositoryImpl @Inject constructor(
    private val googleLoginService: GoogleLoginService,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GoogleLoginAuthInfoRepository {

    override suspend fun loginRequest(idToken: String): GoogleLoginAuthInfo =
        withContext(ioDispatcher) {
            return@withContext googleLoginService.login(GoogleLoginRequest(idToken)).toEntity()
        }
}