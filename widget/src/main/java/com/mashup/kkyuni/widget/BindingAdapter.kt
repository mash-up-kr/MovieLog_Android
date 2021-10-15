package com.mashup.kkyuni.widget

import android.view.View
import androidx.databinding.BindingAdapter
import com.mashup.kkyuni.widget.custom.ThrottleClickListener

@BindingAdapter(requireAll = false, value = ["onThrottleClick", "throttleInterval"])
fun onThrottleClick(
    view: View,
    listener: View.OnClickListener,
    interval: Long?
) {
    view.setOnClickListener(
        ThrottleClickListener(
            listener,
            interval ?: 500L
        )
    )
}

@BindingAdapter("visible")
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}