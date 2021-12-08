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
import com.mashup.kkyuni.feature.playlist.presentation.databinding.HolderEmptyChoiceDateBinding

class ChoiceDateAdapter: ListAdapter<ChoiceDate, RecyclerView.ViewHolder>(DiffCallback) {
    private object DiffCallback : DiffUtil.ItemCallback<ChoiceDate>() {
        override fun areItemsTheSame(oldItem: ChoiceDate, newItem: ChoiceDate) =
            newItem == oldItem

        override fun areContentsTheSame(oldItem: ChoiceDate, newItem: ChoiceDate) =
            newItem == oldItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_DATE -> {
                DateViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.holder_choice_date,
                        parent,
                        false
                    )
                )
            }
            TYPE_EMPTY_DATE -> {
                EmptyDateViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.holder_empty_choice_date,
                        parent,
                        false
                    )
                )
            }
            else -> throw Exception("Undefined view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is DateViewHolder){
            holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val isEmptyDate = getItem(position).date.run {
            year == -1 || month == -1
        }
        return if(isEmptyDate) TYPE_EMPTY_DATE else TYPE_DATE
    }

    companion object {
        const val TYPE_DATE = 0
        const val TYPE_EMPTY_DATE = 1
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

    class EmptyDateViewHolder(
        binding: HolderEmptyChoiceDateBinding
    ): RecyclerView.ViewHolder(binding.root)
}