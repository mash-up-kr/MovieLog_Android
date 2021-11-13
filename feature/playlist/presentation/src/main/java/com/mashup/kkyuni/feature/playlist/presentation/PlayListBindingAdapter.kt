package com.mashup.kkyuni.feature.playlist.presentation

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mashup.kkyuni.core.constant.Constant
import com.mashup.kkyuni.feature.playlist.domain.model.ChoiceDate
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.feature.playlist.presentation.widget.ChoiceDateAdapter

@BindingAdapter("updatePlayList")
fun bindPlayList(view: RecyclerView, list: List<MusicModel>?) {
    if (!list.isNullOrEmpty()) {
        (view.adapter as? PlayListAdapter)?.updatePlayList(list)
    }
}

@BindingAdapter("setMusicThumbnail")
fun bindMusicThumbnail(view: ImageView, url: String) {
    view.load(url) {
        placeholder(R.drawable.ic_thumbnail_music)
        error(R.drawable.ic_thumbnail_music)
    }

    // dim 처리
    view.setColorFilter(
        ContextCompat.getColor(view.context, R.color.black_alpha10),
        PorterDuff.Mode.OVERLAY
    )
}

@BindingAdapter("toastMessage")
fun showToastMessage(view: View, message: String?) {
    if (message.isNullOrBlank()) return

    Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
}

@BindingAdapter("setEmotionImage")
fun bindEmotionImage(view: ImageView, emotion: String) {
    view.load(getImageResourceIdByEmotion(emotion))
}

fun getImageResourceIdByEmotion(emotion: String): Int {
    return when (emotion.uppercase()) {
        Constant.Emotion.NORMAL.name -> R.drawable.ic_emotion_normal
        Constant.Emotion.MAD.name -> R.drawable.ic_emotion_mad
        Constant.Emotion.PANIC.name -> R.drawable.ic_emotion_panic
        Constant.Emotion.SAD.name -> R.drawable.ic_emotion_sad
        else -> R.drawable.ic_emotion_happy
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setDateText")
fun bindDateText(view: TextView, date: Date) {
    view.text = "${date.year}.${date.month}"
}

@BindingAdapter("bindSelectedDate")
fun TextView.bindSelectedDate(isSelected: Boolean){
    setTextAppearance(
        if(isSelected) {
            R.style.ChoiceDateSelected
        }else {
            R.style.ChoiceDateUnSelected
        }
    )
}

@BindingAdapter("updateChoiceDates")
fun RecyclerView.updateChoiceDates(list: List<ChoiceDate>?) {
    list?.let {
        (adapter as? ChoiceDateAdapter)?.submitList(it)
    }
}

@BindingAdapter("scrollToPosition")
fun RecyclerView.scrollToPosition(position: Int){
    smoothScrollToPosition(position)
}