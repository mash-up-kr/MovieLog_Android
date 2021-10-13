package com.mashup.kkyuni.feature.login.data.source

import com.mashup.kkyuni.feature.login.data.GoogleLoginRequest
import com.mashup.kkyuni.feature.login.data.GoogleLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleLoginService {

    @POST("login/google")
    suspend fun login(@Body googleLoginRequest: GoogleLoginRequest): GoogleLoginResponse
}