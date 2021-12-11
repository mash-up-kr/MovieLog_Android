package com.mashup.kkyuni.feature.playlist.domain.usecase

import com.mashup.kkyuni.feature.playlist.domain.model.ChoiceDate
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import javax.inject.Inject

/**
 * year, month를 받고 전 후 6개월의 리스트를 반환
 */
class GetDateListUseCase @Inject constructor() {

    operator fun invoke(year: Int, month: Int) = flow {
        val dates = mutableListOf<ChoiceDate>()

        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)

            add(Calendar.MONTH, -(ITEM_COUNT / 2))
        }

        repeat(ITEM_COUNT) {
            val calendarYear = calendar.get(Calendar.YEAR)
            val calendarMonth = calendar.get(Calendar.MONTH)

            dates.add(
                ChoiceDate(
                    Date(
                        if(calendarMonth == 0){
                            calendarYear - 1
                        }else {
                            calendarYear
                        },
                        if(calendarMonth == 0){
                            12
                        }else {
                            calendarMonth
                        }
                    ),
                    it == ITEM_COUNT / 2
                )
            )
            calendar.add(Calendar.MONTH, 1)
        }

        emit(dates)
    }

    companion object {
        const val ITEM_COUNT = 13
    }
}