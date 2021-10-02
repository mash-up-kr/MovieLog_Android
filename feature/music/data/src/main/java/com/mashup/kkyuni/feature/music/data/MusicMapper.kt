package com.mashup.kkyuni.feature.music.data

import com.mashup.kkyuni.feature.music.data.model.*
import com.mashup.kkyuni.feature.music.domain.model.*
import com.mashup.kkyuni.music.data.model.VideoThumbnailResponse

fun VideosResponse.toEntity(): Videos {
    return Videos(
        kind = this.kind,
        nextPageToken = this.nextPageToken,
        prevPageToken = this.prevPageToken,
        pageInfo = this.pageInfoResponse.toEntity(),
        items = this.items.map {
            Video(
                kind = it.kind,
                id = it.idResponse.toEntity(),
                snippet = it.snippetResponse.toEntity()
            )
        }
    )
}

fun VideoThumbnailResponse.toEntity(): VideoThumbnail {
    return VideoThumbnail(
        default = this.default.toEntity(),
        medium = this.medium.toEntity(),
        large = this.large.toEntity()
    )
}

fun ThumbnailUrlResponse.toEntity(): ThumbnailUrl {
    return ThumbnailUrl(
        url = this.url,
        width = this.width,
        height = this.height
    )
}

fun VideoIdResponse.toEntity(): VideoId {
    return VideoId(
        kind = this.kind,
        videoId = this.videoId,
        channelId = this.channelId,
        playlistId = this.playlistId
    )
}

fun VideoPageInfoResponse.toEntity(): VideoPageInfo {
    return VideoPageInfo(
        totalResults = this.totalResults,
        resultsPerPage = this.resultsPerPage
    )
}

fun VideoResponse.toEntity(): Video {
    return Video(
        kind = this.kind,
        id = this.idResponse.toEntity(),
        snippet = this.snippetResponse.toEntity()
    )
}

fun VideoSnippetResponse.toEntity(): VideoSnippet {
    return VideoSnippet(
        publishedAt = this.publishedAt,
        channelId = this.channelId,
        title = this.title,
        description = this.description,
        thumbnails = this.thumbnails.toEntity(),
        channelTitle = this.channelTitle
    )
}