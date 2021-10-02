package com.mashup.kkyuni.music.data.service

import com.mashup.kkyuni.music.data.model.Videos
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchVideoService {

    @GET("/search")
    suspend fun searchVideo(
        @Query("part") party: String,
        @Query("q") query: String,
        @Query("key") key: String,
    ): Flow<com.mashup.kkyuni.music.data.model.Videos>

}