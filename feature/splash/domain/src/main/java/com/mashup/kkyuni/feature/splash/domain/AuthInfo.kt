package com.mashup.kkyuni.feature.splash.domain

data class AuthInfo(
    val memberId: Int,
    val refreshToken: String,
    val token: String
)
