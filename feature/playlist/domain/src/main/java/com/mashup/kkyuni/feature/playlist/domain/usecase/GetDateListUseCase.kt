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
            dates.add(
                ChoiceDate(
                    Date(
                        if(calendar.get(Calendar.MONTH) == 0){
                            calendar.get(Calendar.YEAR) - 1
                        }else {
                            calendar.get(Calendar.YEAR)
                        },
                        if(calendar.get(Calendar.MONTH) == 0){
                            12
                        }else {
                            calendar.get(Calendar.MONTH)
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