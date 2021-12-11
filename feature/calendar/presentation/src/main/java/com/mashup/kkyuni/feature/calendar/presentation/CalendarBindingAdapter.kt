package com.mashup.kkyuni.feature.calendar.presentation

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.calendar.domain.model.CalendarDate

@BindingAdapter("updateCalendarDates")
fun RecyclerView.updateChoiceDates(list: List<CalendarDate>?) {
	list?.let {
		(adapter as? KobaCalendarDayAdapter)?.submitList(it)
	}
}