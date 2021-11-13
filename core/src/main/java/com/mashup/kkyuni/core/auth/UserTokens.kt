package com.mashup.kkyuni.core.auth

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
data class UserTokens(
    val memberId: Int,
    val refreshToken: String,
    val accessToken: String
) {

    companion object {
        private val jsonAdapter = Moshi.Builder().build().adapter(UserTokens::class.java)

        fun toObject(json: String): UserTokens? {
            return jsonAdapter.fromJson(json)
        }
    }

    fun toJson(): String {
        return jsonAdapter.toJson(this)
    }
}
