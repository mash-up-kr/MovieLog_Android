package com.mashup.kkyuni.feature.writing.presentation.preview

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.mashup.kkyuni.feature.writing.domain.model.UploadState

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("showUploading")
    fun ProgressBar.showUploading(uploadState: UploadState) {
        isVisible = uploadState is UploadState.Uploading
    }
}