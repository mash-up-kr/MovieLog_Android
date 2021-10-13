package com.mashup.kkyuni.feature.login.domain

data class GoogleLoginAuthInfo(
    val memberId: Int,
    val refreshToken: String,
    val sub: String,
    val token: String
)