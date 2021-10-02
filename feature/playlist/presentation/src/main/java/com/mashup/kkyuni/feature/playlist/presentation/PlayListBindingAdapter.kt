package com.mashup.kkyuni.feature.playlist.presentation

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.playlist.domain.model.PlayListState

@BindingAdapter("updatePlayList")
fun RecyclerView.updatePlayList(viewState: PlayListState){
    (viewState as? PlayListState.Success)?.let { success ->
        adapter?.let {
            if(it is PlayListAdapter){
                it.updatePlayList(success.list)
            }
        }
    }
}