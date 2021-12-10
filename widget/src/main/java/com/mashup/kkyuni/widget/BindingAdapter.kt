package com.mashup.kkyuni.widget

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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

@BindingAdapter("scrollToPosition")
fun RecyclerView.requestScrollToPosition(position: Int){
    if(position != RecyclerView.NO_POSITION) {
        scrollToPosition(position)
    }
}