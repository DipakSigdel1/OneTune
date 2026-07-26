package com.music.app.core.domain.model

data class Song(
    val id: String,                 // Primary unique identifier
    val youtubeId: String? = null,   // Associated YouTube video ID
    val spotifyId: String? = null,   // Associated Spotify track ID
    val title: String,
    val artist: String,
    val album: String? = null,
    val durationMs: Long,
    val artworkUrl: String?,
    val streamUrl: String? = null,  // Extracted direct audio stream URL
    val isExplicit: Boolean = false,
    val isLiked: Boolean = false,
    val isCached: Boolean = false,
    val isDownloaded: Boolean = false
)
