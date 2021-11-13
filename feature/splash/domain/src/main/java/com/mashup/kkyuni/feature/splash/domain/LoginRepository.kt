package com.mashup.kkyuni.feature.splash.domain

interface LoginRepository {

    suspend fun login(): AuthInfo
}