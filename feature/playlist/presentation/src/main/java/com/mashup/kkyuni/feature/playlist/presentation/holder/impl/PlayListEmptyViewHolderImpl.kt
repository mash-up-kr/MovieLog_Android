package com.mashup.kkyuni.feature.playlist.presentation.holder.impl

import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.feature.playlist.presentation.PlayListViewModel
import com.mashup.kkyuni.feature.playlist.presentation.databinding.HolderPlayListEmptyBinding
import com.mashup.kkyuni.feature.playlist.presentation.holder.PlayListViewHolder

class PlayListEmptyViewHolderImpl(
    private val binding: HolderPlayListEmptyBinding,
    private val viewModel: PlayListViewModel
) : RecyclerView.ViewHolder(binding.root), PlayListViewHolder {
    init {
        binding.viewModel = viewModel
    }

    override fun bind(item: MusicModel) {
        binding.executePendingBindings()
    }
}