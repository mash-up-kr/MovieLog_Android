package com.mashup.kkyuni.playlist.presenter.holder.impl

import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.databinding.HolderPlayListEmptyBinding
import com.mashup.kkyuni.playlist.domain.model.PlayList
import com.mashup.kkyuni.playlist.presenter.holder.PlayListViewHolder

class PlayListEmptyViewHolderImpl(
    private val binding: HolderPlayListEmptyBinding
): RecyclerView.ViewHolder(binding.root), PlayListViewHolder{

    override fun bind(item: PlayList) {
        binding.executePendingBindings()
    }
}