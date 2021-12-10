package com.mashup.kkyuni.feature.calendar.domain

import com.mashup.kkyuni.feature.calendar.domain.model.CalendarDate
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * 전달받은 날짜 기준으로 +20일 반환
 */
class GetAfterDateUseCase @Inject constructor(){
	operator fun invoke(year: Int, month: Int, day: Int) = flow{
		val calendar = Calendar.getInstance().apply {
			set(Calendar.YEAR, year)
			set(Calendar.MONTH, month - 1)
			set(Calendar.DATE, day)
		}

		val dateList = mutableListOf<CalendarDate>()

		repeat(ITEM_COUNT){
			calendar.add(Calendar.DATE, 1)

			dateList.add(
				CalendarDate(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DATE)
				)
			)
		}

		emit(dateList)
	}

	companion object {
		const val ITEM_COUNT = 20
	}
}
