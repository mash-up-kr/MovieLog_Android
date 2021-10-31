package com.mashup.kkyuni.feature.writing.presentation.emotion

import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.mashup.kkyuni.core.constant.Constant.Emotion
import com.mashup.kkyuni.feature.writing.domain.model.Writing
import com.mashup.kkyuni.feature.writing.presentation.R

@BindingAdapter("visibleMad")
fun View.visibleMad(emotion: Emotion){
    setBackgroundResource(
        if(emotion == Emotion.MAD){
            R.drawable.background_emotion
        }else {
            R.drawable.background_emotion_unselected
        }
    )
}

@BindingAdapter("visibleHappy")
fun View.visibleHappy(emotion: Emotion){
    setBackgroundResource(
        if(emotion == Emotion.HAPPY){
            R.drawable.background_emotion
        }else {
            R.drawable.background_emotion_unselected
        }
    )
}

@BindingAdapter("visibleNormal")
fun View.visibleNormal(emotion: Emotion){
    setBackgroundResource(
        if(emotion == Emotion.NORMAL){
            R.drawable.background_emotion
        }else {
            R.drawable.background_emotion_unselected
        }
    )
}

@BindingAdapter("visiblePanic")
fun View.visiblePanic(emotion: Emotion){
    setBackgroundResource(
        if(emotion == Emotion.PANIC){
            R.drawable.background_emotion
        }else {
            R.drawable.background_emotion_unselected
        }
    )
}

@BindingAdapter("visibleSad")
fun View.visibleSad(emotion: Emotion){
    setBackgroundResource(
        if(emotion == Emotion.SAD){
            R.drawable.background_emotion
        }else {
            R.drawable.background_emotion_unselected
        }
    )
}