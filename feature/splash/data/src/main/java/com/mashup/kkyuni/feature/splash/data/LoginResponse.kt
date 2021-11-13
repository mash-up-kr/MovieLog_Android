package com.mashup.kkyuni.feature.splash.data

import com.mashup.kkyuni.feature.splash.domain.AuthInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "memberID") val memberId: Int,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "token") val token: String
)

fun LoginResponse.toEntity() =
    AuthInfo(memberId, refreshToken, token)