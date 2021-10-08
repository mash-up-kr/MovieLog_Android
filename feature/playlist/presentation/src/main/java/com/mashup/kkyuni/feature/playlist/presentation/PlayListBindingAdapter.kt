package com.mashup.kkyuni.feature.playlist.presentation

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel

@BindingAdapter("updatePlayList")
fun bindPlayList(view: RecyclerView, list: List<MusicModel>?) {
    if(!list.isNullOrEmpty()) {
        (view.adapter as? PlayListAdapter)?.updatePlayList(list)
    }
}

@BindingAdapter("setMusicThumbnail")
fun bindMusicThumbnail(view: ImageView, url: String){
    view.load(url)
}

@BindingAdapter("toastMessage")
fun showToastMesage(view: View, message: String?){
    if(message.isNullOrBlank()) return

    Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
}