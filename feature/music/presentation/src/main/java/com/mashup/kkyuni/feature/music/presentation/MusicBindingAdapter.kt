package com.mashup.kkyuni.feature.music.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setMusicThumbnail")
fun ImageView.setMusicThumbnail(url: String) {
    load(url) {
    }
}

@BindingAdapter("setHtmlString")
fun TextView.setHtmlString(htmlString: String) {
    text = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_COMPACT)
}