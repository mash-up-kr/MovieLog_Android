package com.mashup.kkyuni.feature.setting.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.setting.presentation.databinding.ItemSettingBinding

class SettingAdapter(private val viewModel: SettingViewModel) :
    ListAdapter<String, SettingViewHolder>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        return SettingViewHolder(
            ItemSettingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        with(holder.binding) {
            title = getItem(position)
            viewModel = this@SettingAdapter.viewModel
        }
    }
}

class SettingViewHolder(val binding: ItemSettingBinding) : RecyclerView.ViewHolder(binding.root)
