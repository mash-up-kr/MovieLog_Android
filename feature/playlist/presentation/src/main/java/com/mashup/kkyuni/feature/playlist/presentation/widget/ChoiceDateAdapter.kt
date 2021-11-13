package com.mashup.kkyuni.feature.playlist.presentation.widget

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.presentation.R
import com.mashup.kkyuni.feature.playlist.presentation.databinding.HolderChoiceDateBinding

class ChoiceDateAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dates: List<Date> = listOf(
        Date(2021, 1),
        Date(2021, 2),
        Date(2021, 3),
        Date(2021, 4),
        Date(2021, 6),
        Date(2021, 7),
        Date(2021, 8),
        Date(2021, 9),
        Date(2021, 10),
        Date(2021, 11),
        Date(2021, 12)
    )

    @SuppressLint("NotifyDataSetChanged")
    private fun updateDateList(list: List<Date>){
        dates.toMutableList().run {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.holder_choice_date,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? DateViewHolder)?.bind(dates[position])
    }

    override fun getItemCount() = dates.size

    class DateViewHolder(
        private val binding: HolderChoiceDateBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(date: Date){
            binding.run {
                this.date = date
                executePendingBindings()
            }
        }
    }
}