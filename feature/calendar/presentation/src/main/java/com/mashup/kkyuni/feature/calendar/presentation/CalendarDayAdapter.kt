package com.mashup.kkyuni.feature.calendar.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.calendar.presentation.databinding.ItemCalendarDayBinding

class CalendarDayAdapter : ListAdapter<String, CalendarDayViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CalendarDayViewHolder(
        ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CalendarDayViewHolder, position: Int) {
        with(holder.binding) {
            day.text = getItem(position)
        }
    }
}

class CalendarDayViewHolder(val binding: ItemCalendarDayBinding) :
    RecyclerView.ViewHolder(binding.root)