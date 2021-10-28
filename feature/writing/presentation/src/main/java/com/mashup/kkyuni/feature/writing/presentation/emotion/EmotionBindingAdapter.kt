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
    visibility = if(emotion == Emotion.MAD) {
        View.VISIBLE
    }else {
        View.GONE
    }
}

@BindingAdapter("visibleHappy")
fun View.visibleHappy(emotion: Emotion){
    visibility = if(emotion == Emotion.HAPPY) {
        View.VISIBLE
    }else {
        View.GONE
    }
}

@BindingAdapter("visibleNormal")
fun View.visibleNormal(emotion: Emotion){
    visibility = if(emotion == Emotion.NORMAL) {
        View.VISIBLE
    }else {
        View.GONE
    }
}

@BindingAdapter("visiblePanic")
fun View.visiblePanic(emotion: Emotion){
    visibility = if(emotion == Emotion.PANIC) {
        View.VISIBLE
    }else {
        View.GONE
    }
}

@BindingAdapter("visibleSad")
fun View.visibleSad(emotion: Emotion){
    visibility = if(emotion == Emotion.SAD) {
        View.VISIBLE
    }else {
        View.GONE
    }
}

@BindingAdapter("enabledButton")
fun Button.enabledButton(isEnabled: Boolean){
    this.isEnabled = isEnabled

    setTextColor(
        ContextCompat.getColor(
            context,
            if(isEnabled) R.color.white else R.color.white_alpha50
        )
    )

    setBackgroundResource(
        if(isEnabled) R.color.purple_700 else R.color.purple_700_alpha50
    )
}
