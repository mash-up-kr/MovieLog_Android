package com.mashup.kkyuni.feature.calendar.domain

import com.mashup.kkyuni.feature.calendar.domain.model.CalendarDate
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * 전달된 날로 부터 +-10일 만큼의 CalendarDateList를 반환
 */
class GetCalendarDateUseCase @Inject constructor() {

	operator fun invoke(year: Int, month: Int, day: Int) = flow {
		val calendar = Calendar.getInstance().apply {
			set(Calendar.YEAR, year)
			set(Calendar.MONTH, month - 1)
			set(Calendar.DATE, day)

			add(Calendar.DATE, -(ITEM_COUNT / 2))
		}

		val calendarDateList = mutableListOf<CalendarDate>()

		repeat(ITEM_COUNT) {
			calendarDateList.add(
				CalendarDate(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DATE)
				)
			)

			calendar.add(Calendar.DATE, 1)
		}

		emit(calendarDateList)
	}

	companion object {
		const val ITEM_COUNT = 21
	}
}