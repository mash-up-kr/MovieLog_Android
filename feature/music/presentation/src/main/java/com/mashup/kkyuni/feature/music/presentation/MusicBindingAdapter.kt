package com.mashup.kkyuni.feature.music.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setMusicThumbnail")
fun ImageView.setMusicThumbnail(url: String) {
    load(url) {
    }
}