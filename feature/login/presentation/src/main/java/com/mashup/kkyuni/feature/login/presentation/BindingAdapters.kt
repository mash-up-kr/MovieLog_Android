package com.mashup.kkyuni.feature.login.presentation

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("show")
    fun ProgressBar.bindShow(show: Boolean) {
        isVisible = show
    }

    @JvmStatic
    @BindingAdapter("showToast")
    fun View.showToast(message: String?) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}