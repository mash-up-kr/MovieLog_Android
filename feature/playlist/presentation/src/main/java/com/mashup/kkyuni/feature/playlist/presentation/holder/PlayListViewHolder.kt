package com.mashup.kkyuni.feature.playlist.presentation.holder

import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel

interface PlayListViewHolder {
    fun bind(item: MusicModel)
}