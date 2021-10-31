package com.mashup.kkyuni.feature.music.data

import com.mashup.kkyuni.feature.music.data.model.*
import com.mashup.kkyuni.feature.music.domain.model.*

fun VideosResponse.toEntity(): Videos {
    return Videos(
        kind = this.kind,
        nextPageToken = this.nextPageToken,
        prevPageToken = this.prevPageToken ?: "",
        pageInfo = this.pageInfoResponse.toEntity(),
        items = this.items.map {
            Video(
                kind = it.kind,
                id = it.idResponse.toEntity(),
                snippet = it.snippet.toEntity(),
            )
        }
    )
}

fun VideoThumbnailResponse.toEntity(): VideoThumbnail {
    return VideoThumbnail(
        default = this.default.toEntity(),
        medium = this.medium.toEntity(),
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
        snippet = this.snippet.toEntity()
    )
}

fun VideoSnippetResponse.toEntity(): VideoSnippet {
    return VideoSnippet(
        publishedAt = this.publishedAt,
        title = this.title,
        description = this.description,
        thumbnails = this.thumbnails.toEntity(),
        channelTitle = this.channelTitle,
    )
}

fun ContentDetailResponse.toEntity(): ContentDetail {
    return ContentDetail(
        duration = this.duration
    )
}

fun VideoDetailResponse.toEntity(): VideoDetail {
    return VideoDetail(
        id = this.id,
        contentDetail = this.contentDetails.toEntity()
    )
}

fun VideoDetailsResponse.toEntity(): VideoDetails {
    return VideoDetails(
        items = this.items.map {
            it.toEntity()
        }
    )
}