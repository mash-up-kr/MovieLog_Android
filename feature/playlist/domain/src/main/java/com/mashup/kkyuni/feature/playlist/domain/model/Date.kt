package com.mashup.kkyuni.feature.playlist.domain.model

data class Date(
    val year: Int,
    val month: Int
) {
    override fun equals(other: Any?): Boolean {
        return if(other is Date){
            other.year == year && other.month == month
        }else {
            false
        }
    }
}