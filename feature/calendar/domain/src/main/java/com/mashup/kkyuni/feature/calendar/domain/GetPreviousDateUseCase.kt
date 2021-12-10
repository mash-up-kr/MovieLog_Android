package com.mashup.kkyuni.feature.calendar.domain

import com.mashup.kkyuni.feature.calendar.domain.model.CalendarDate
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * 전달받은 날짜 기준으로 -20일 반환
 */
class GetPreviousDateUseCase @Inject constructor(){
	operator fun invoke(year: Int, month: Int, day: Int) = flow{
		val calendar = Calendar.getInstance().apply {
			set(Calendar.YEAR, year)
			set(Calendar.MONTH, month - 1)
			set(Calendar.DATE, day)

			add(Calendar.DATE, -ITEM_COUNT)
		}

		val dateList = mutableListOf<CalendarDate>()

		repeat(ITEM_COUNT){
			dateList.add(
				CalendarDate(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DATE)
				)
			)

			calendar.add(Calendar.DATE, 1)
		}

		emit(dateList)
	}

	companion object {
		const val ITEM_COUNT = 20
	}
}