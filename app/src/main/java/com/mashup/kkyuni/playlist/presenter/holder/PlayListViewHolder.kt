package com.mashup.kkyuni.playlist.presenter.holder

import com.mashup.kkyuni.playlist.domain.model.MusicModel

interface PlayListViewHolder {
    fun bind(item: MusicModel)
}