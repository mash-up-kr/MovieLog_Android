package com.mashup.kkyuni.feature.writing.domain.model

sealed class UploadState {
    object Uploading : UploadState()

    object Complete : UploadState()
}