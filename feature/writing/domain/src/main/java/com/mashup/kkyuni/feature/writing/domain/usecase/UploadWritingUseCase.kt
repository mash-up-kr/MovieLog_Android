package com.mashup.kkyuni.feature.writing.domain.usecase

import com.mashup.kkyuni.feature.writing.domain.model.Diary
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UploadWritingUseCase @Inject constructor(

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

    suspend operator fun invoke(params: Params) = flow {
        emit(Diary())
    }
}