package com.mashup.kkyuni.feature.calendar.domain.model

data class CalendarDate(
	val year: Int,
	val month: Int,
	val day: Int
) {
	override fun equals(other: Any?): Boolean {
		return if(other is CalendarDate){
			year == other.year
				&& month == other.month
				&& day == other.day
		}else {
			false
		}
	}
}