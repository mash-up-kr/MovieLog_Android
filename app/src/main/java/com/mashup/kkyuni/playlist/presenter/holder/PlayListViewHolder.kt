package com.mashup.kkyuni.playlist.presenter.holder

import com.mashup.kkyuni.playlist.domain.model.PlayList

interface PlayListViewHolder {
    fun bind(item: PlayList)
}