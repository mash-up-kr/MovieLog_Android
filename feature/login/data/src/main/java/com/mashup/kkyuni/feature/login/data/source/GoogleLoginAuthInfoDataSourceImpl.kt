package com.mashup.kkyuni.feature.login.data.source

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.mashup.kkyuni.feature.login.domain.source.GoogleLoginAuthInfoDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.mashup.kkyuni.core.CoroutineDispatcherModule.DispatcherIO
import com.mashup.kkyuni.feature.login.domain.source.LoginPreferenceManager

class GoogleLoginAuthInfoDataSourceImpl @Inject constructor(
    private val loginPreferenceManager: LoginPreferenceManager,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GoogleLoginAuthInfoDataSource {

    override suspend fun setGoogleLoginAuthInfo(googleLoginAuthInfo: GoogleLoginAuthInfo) {
        withContext(ioDispatcher) {
            loginPreferenceManager.setGoogleLoginAuthInfo(googleLoginAuthInfo)
        }
    }

    override suspend fun getGoogleLoginAuthInfo(): GoogleLoginAuthInfo? =
        withContext(ioDispatcher) {
            loginPreferenceManager.getGoogleLoginAuthInfo()
        }
}
