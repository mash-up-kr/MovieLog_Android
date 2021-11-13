package com.mashup.kkyuni.feature.playlist.domain.usecase

import com.mashup.kkyuni.feature.playlist.domain.model.ChoiceDate
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class GetPreviousDateListUseCase @Inject constructor() {

    operator fun invoke(year: Int, month: Int) = flow {
        val dates = mutableListOf<ChoiceDate>()

        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)

            add(Calendar.YEAR, -1)
        }

        repeat(ITEM_COUNT){
            dates.add(
                ChoiceDate(
                    Date(
                        if(calendar.get(Calendar.MONTH) == 0){
                            calendar.get(Calendar.YEAR) -1
                        }else {
                            calendar.get(Calendar.YEAR)
                        }
                        ,
                        if(calendar.get(Calendar.MONTH) == 0){
                            12
                        }else {
                            calendar.get(Calendar.MONTH)
                        }
                    )
                )
            )

            calendar.add(Calendar.MONTH, 1)
        }

        emit(dates)
    }

    companion object {
        const val ITEM_COUNT = 12
    }
}
