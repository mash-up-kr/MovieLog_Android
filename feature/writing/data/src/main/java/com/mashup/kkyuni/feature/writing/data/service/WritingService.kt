package com.mashup.kkyuni.feature.writing.data.service

import com.mashup.kkyuni.feature.writing.data.dto.UploadResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WritingService {
    @FormUrlEncoded
    @POST("api/v1/diary")
    fun createDiary(
        @Field("content")
        content: String,
        @Field("diaryType")
        diaryType: String,
        @Field("emotion")
        emotion: String,
        @Field("musicPlayTime")
        musicPlayTime: Int,
        @Field("musicThumbnailImage")
        musicThumbnailImage: String,
        @Field("musicTitle")
        musicTitle: String,
        @Field("title")
        title: String,
        @Field("youtubeLink")
        youtubeLink: String
    ): UploadResponseDto
}