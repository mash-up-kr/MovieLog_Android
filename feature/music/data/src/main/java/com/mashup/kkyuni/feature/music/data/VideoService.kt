package com.mashup.kkyuni.feature.music.data

import com.mashup.kkyuni.feature.music.data.model.VideoDetailsResponse
import com.mashup.kkyuni.feature.music.data.model.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {
    @GET("search")
    suspend fun searchVideo(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("key") key: String = "AIzaSyD_FgKW4mEpbNKMA09TrGhNd_QZhlqBK-M",
    ): VideosResponse

    @GET("videos")
    suspend fun getVideoInfo(
        @Query("part") part: String = "contentDetails",
        @Query("id") id: String,
        @Query("key") key: String = "AIzaSyD_FgKW4mEpbNKMA09TrGhNd_QZhlqBK-M"
    ): VideoDetailsResponse
}