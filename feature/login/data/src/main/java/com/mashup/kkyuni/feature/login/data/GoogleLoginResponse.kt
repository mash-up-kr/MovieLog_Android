package com.mashup.kkyuni.feature.login.data

import com.mashup.kkyuni.feature.login.domain.GoogleLoginAuthInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoogleLoginResponse(
    @Json(name = "memberID") val memberId: Int,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "sub") val sub: String,
    @Json(name = "token") val token: String
)

fun GoogleLoginResponse.toEntity() = GoogleLoginAuthInfo(memberId, refreshToken, sub, token)