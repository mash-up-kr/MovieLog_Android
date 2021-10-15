package com.mashup.kkyuni.widget.custom

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ThrottleClickListener(
    private val clickListener: View.OnClickListener,
    private val interval: Long
) : View.OnClickListener {
    private var clickable = true

    override fun onClick(view: View) {
        if (clickable) {
            clickable = false
            CoroutineScope(Dispatchers.Main).launch {
                delay(interval)
                clickable = true
            }
            clickListener.onClick(view)
        }
    }
}