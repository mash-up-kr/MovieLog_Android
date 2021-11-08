package com.mashup.kkyuni.data.network.auth

import com.mashup.kkyuni.core.auth.UserTokens
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("login/refresh")
    suspend fun refreshToken(@Header("Authorization") refreshToken: String): UserTokens
}