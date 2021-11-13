package com.mashup.kkyuni.feature.splash.data

import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    suspend fun login(@Header("Authorization") token: String): LoginResponse
}