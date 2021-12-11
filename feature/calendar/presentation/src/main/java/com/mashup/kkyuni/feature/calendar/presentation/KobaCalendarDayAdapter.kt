package com.mashup.kkyuni.feature.calendar.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.calendar.domain.model.CalendarDate
import com.mashup.kkyuni.feature.calendar.presentation.databinding.ItemKobaCalendarDayBinding

class KobaCalendarDayAdapter: ListAdapter<CalendarDate, KobaCalendarDayViewHolder>(DiffCallback) {

	private object DiffCallback : DiffUtil.ItemCallback<CalendarDate>() {
		override fun areItemsTheSame(oldItem: CalendarDate, newItem: CalendarDate) =
			newItem == oldItem

		override fun areContentsTheSame(oldItem: CalendarDate, newItem: CalendarDate) =
			newItem == oldItem
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KobaCalendarDayViewHolder(
		ItemKobaCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
	)

	override fun onBindViewHolder(holder: KobaCalendarDayViewHolder, position: Int) {
		(holder as? KobaCalendarDayViewHolder)?.bind(getItem(position))
	}
}

class KobaCalendarDayViewHolder(
	private val binding: ItemKobaCalendarDayBinding
) : RecyclerView.ViewHolder(binding.root) {
	fun bind(date: CalendarDate){
		binding.run {
			this.day = date.day.toString()
			executePendingBindings()
		}
	}
}