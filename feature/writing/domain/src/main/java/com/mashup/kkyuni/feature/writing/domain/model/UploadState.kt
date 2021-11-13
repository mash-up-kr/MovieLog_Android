package com.mashup.kkyuni.feature.writing.domain.model

sealed class UploadState {
    object Idle : UploadState()
    object Uploading : UploadState()
    object Complete : UploadState()
}