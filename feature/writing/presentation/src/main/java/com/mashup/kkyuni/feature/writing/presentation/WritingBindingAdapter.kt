package com.mashup.kkyuni.feature.writing.presentation

import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter


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
