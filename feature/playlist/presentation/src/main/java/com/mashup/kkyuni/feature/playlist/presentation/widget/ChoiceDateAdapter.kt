package com.mashup.kkyuni.feature.playlist.presentation.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.playlist.domain.model.ChoiceDate
import com.mashup.kkyuni.feature.playlist.presentation.R
import com.mashup.kkyuni.feature.playlist.presentation.databinding.HolderChoiceDateBinding

class ChoiceDateAdapter: ListAdapter<ChoiceDate, RecyclerView.ViewHolder>(DiffCallback) {
    private object DiffCallback : DiffUtil.ItemCallback<ChoiceDate>() {
        override fun areItemsTheSame(oldItem: ChoiceDate, newItem: ChoiceDate) =
            newItem == oldItem

        override fun areContentsTheSame(oldItem: ChoiceDate, newItem: ChoiceDate) =
            newItem == oldItem
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
        (holder as? DateViewHolder)?.bind(getItem(position))
    }

    class DateViewHolder(
        private val binding: HolderChoiceDateBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(date: ChoiceDate){
            binding.run {
                this.choiceDate = date
                executePendingBindings()
            }
        }
    }
}