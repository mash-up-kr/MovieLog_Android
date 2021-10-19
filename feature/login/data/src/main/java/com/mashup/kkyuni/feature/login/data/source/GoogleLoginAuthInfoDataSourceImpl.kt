package com.mashup.kkyuni.feature.login.data.source

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.mashup.kkyuni.core.CoroutineDispatcherModule.DispatcherIO

class GoogleLoginAuthInfoDataSourceImpl @Inject constructor(
    private val loginPreferenceManager: LoginPreferenceManager,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GoogleLoginAuthInfoDataSource {

    override suspend fun setGoogleLoginAuthInfo(googleLoginAuthInfo: GoogleLoginAuthInfo) {
        withContext(ioDispatcher) {
            with(loginPreferenceManager) {
                setMemberId(googleLoginAuthInfo.memberId)
                setRefreshToken(googleLoginAuthInfo.refreshToken)
                setSub(googleLoginAuthInfo.sub)
                setToken(googleLoginAuthInfo.token)
            }
        }
    }

    override suspend fun getGoogleLoginAuthInfo(): GoogleLoginAuthInfo? =
        withContext(ioDispatcher) {
            val memberId = loginPreferenceManager.getMemberId()
            if (memberId < 0) return@withContext null
            val refreshToken = loginPreferenceManager.getRefreshToken() ?: return@withContext null
            val sub = loginPreferenceManager.getSub() ?: return@withContext null
            val token = loginPreferenceManager.getToken() ?: return@withContext null
            return@withContext GoogleLoginAuthInfo(memberId, refreshToken, sub, token)
        }
}
