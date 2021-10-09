package com.mashup.kkyuni.feature.calendar.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.calendar.presentation.databinding.ItemCalendarDayBinding

class CalendarDayAdapter : ListAdapter<CalendarDayModel, CalendarDayViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<CalendarDayModel>() {
        override fun areItemsTheSame(oldItem: CalendarDayModel, newItem: CalendarDayModel) =
            newItem == oldItem

        override fun areContentsTheSame(oldItem: CalendarDayModel, newItem: CalendarDayModel) =
            newItem == oldItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CalendarDayViewHolder(
        ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CalendarDayViewHolder, position: Int) {
        holder.binding.day.text = getItem(position).date.split("-")[2]
    }
}

class CalendarDayViewHolder(val binding: ItemCalendarDayBinding) :
    RecyclerView.ViewHolder(binding.root)