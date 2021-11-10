package com.mashup.kkyuni.feature.writing.domain.usecase

import com.mashup.kkyuni.feature.writing.data.repository.WritingRepository
import com.mashup.kkyuni.feature.writing.domain.toDiary
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UploadWritingUseCase @Inject constructor(
    private val repository: WritingRepository
) {

    data class Params(
        val content: String,
        val diaryType: String,
        val emotion: String,
        val musicPlayTime: Int,
        val musicThumbnailImage: String,
        val musicTitle: String,
        val title: String,
        val youtubeLink: String
    )

    suspend operator fun invoke(params: Params) =
        repository.createDiary(
            params.content,
            params.diaryType,
            params.emotion,
            params.musicPlayTime,
            params.musicThumbnailImage,
            params.musicTitle,
            params.title,
            params.youtubeLink
        ).map {
            it.toDiary()
        }
}