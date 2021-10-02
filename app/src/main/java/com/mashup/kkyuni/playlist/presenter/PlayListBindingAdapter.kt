package com.mashup.kkyuni.playlist.presenter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.playlist.domain.model.PlayList

@BindingAdapter("updatePlayList")
fun RecyclerView.updatePlayList(list: List<PlayList>){
    adapter?.let {
        if(it is PlayListAdapter){
            it.updatePlayList(list)
        }
    }
}