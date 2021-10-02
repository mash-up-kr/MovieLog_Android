package com.mashup.kkyuni.feature.login.data

import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleLoginService {

    @POST("/api/v1/login/google")
    suspend fun login(@Body googleLoginRequestDTO: GoogleLoginRequestDTO): GoogleLoginResponse
}