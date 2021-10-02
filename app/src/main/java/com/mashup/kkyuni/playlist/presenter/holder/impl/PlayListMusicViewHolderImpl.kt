package com.mashup.kkyuni.playlist.presenter.holder.impl

import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.databinding.HolderPlayListMusicBinding
import com.mashup.kkyuni.playlist.domain.model.PlayList
import com.mashup.kkyuni.playlist.presenter.holder.PlayListViewHolder

class PlayListMusicViewHolderImpl(
    private val binding: HolderPlayListMusicBinding
): RecyclerView.ViewHolder(binding.root), PlayListViewHolder {

    override fun bind(item: PlayList) {
        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}