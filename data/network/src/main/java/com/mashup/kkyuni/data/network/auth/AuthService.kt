package com.mashup.kkyuni.data.network.auth

import com.mashup.kkyuni.core.auth.UserTokens
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @GET("login/refresh")
    suspend fun refreshToken(@Header("token") refreshToken: String): UserTokens
}