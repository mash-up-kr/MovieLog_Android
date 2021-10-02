package com.mashup.kkyuni.feature.music.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.music.data.model.VideoResponse
import com.mashup.kkyuni.feature.music.presentation.databinding.ItemMusicBinding

class MusicAdapter(private val viewModel: MusicViewModel) :
    ListAdapter<VideoResponse, MusicViewHolder>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<VideoResponse>() {
        override fun areItemsTheSame(oldItem: VideoResponse, newItem: VideoResponse) = oldItem == newItem

        override fun areContentsTheSame(oldItem: VideoResponse, newItem: VideoResponse) = oldItem == newItem
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
        with(holder.binding) {
            title = getItem(position).snippetResponse.title
            viewModel = this@MusicAdapter.viewModel
        }
    }
}

class MusicViewHolder(val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root)