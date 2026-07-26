package com.music.app.core.data.local

import com.music.app.core.data.local.entity.SongEntity
import com.music.app.core.domain.model.Song

fun SongEntity.toDomain(): Song {
    return Song(
        id = id,
        youtubeId = youtubeId,
        spotifyId = spotifyId,
        title = title,
        artist = artist,
        album = album,
        durationMs = durationMs,
        artworkUrl = artworkUrl,
        streamUrl = streamUrl,
        isExplicit = isExplicit,
        isLiked = isLiked,
        isDownloaded = isDownloaded
    )
}

fun Song.toEntity(lastPlayed: Long = 0L, playCount: Int = 0): SongEntity {
    return SongEntity(
        id = id,
        youtubeId = youtubeId,
        spotifyId = spotifyId,
        title = title,
        artist = artist,
        album = album,
        durationMs = durationMs,
        artworkUrl = artworkUrl,
        streamUrl = streamUrl,
        isExplicit = isExplicit,
        isLiked = isLiked,
        isDownloaded = isDownloaded,
        lastPlayedTimestamp = lastPlayed,
        playCount = playCount
    )
}
