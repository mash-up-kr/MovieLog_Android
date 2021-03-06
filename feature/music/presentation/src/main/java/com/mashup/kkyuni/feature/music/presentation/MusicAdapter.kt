package com.mashup.kkyuni.feature.music.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.music.domain.model.Video
import com.mashup.kkyuni.feature.music.presentation.databinding.ItemMusicBinding

class MusicAdapter constructor(private val viewModel: MusicViewModel) :
    ListAdapter<Video, MusicAdapter.MusicViewHolder>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        if (position == viewModel.selectedItemPos) {
            holder.selectedBackground()
        } else {
            holder.defaultBackground()
        }
        holder.bind(getItem(position))
    }

    inner class MusicViewHolder(val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    viewModel.selectedItemPos = adapterPosition
                    notifyDataSetChanged()
                    viewModel.setSelectedItem(getItem(viewModel.selectedItemPos))
                }
            }
        }

        fun bind(video: Video) {
            binding.apply {
                this.musicViewModel = viewModel
                this.video = video
                executePendingBindings()
            }
        }

        fun defaultBackground() {
            binding.recyclerItem.isSelected = false
        }

        fun selectedBackground() {
            binding.recyclerItem.isSelected = true
        }
    }
}

