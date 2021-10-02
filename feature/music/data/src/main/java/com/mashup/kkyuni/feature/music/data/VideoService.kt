package com.mashup.kkyuni.feature.music.data

import com.mashup.kkyuni.music.data.model.Videos
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {
    @GET("/search")
    suspend fun searchVideo(
        @Query("part") party: String,
        @Query("q") query: String,
        @Query("key") key: String,
    ): Flow<Videos>
}