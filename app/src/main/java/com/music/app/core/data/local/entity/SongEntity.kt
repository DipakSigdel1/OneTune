package com.music.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val id: String,
    val youtubeId: String?,
    val spotifyId: String?,
    val title: String,
    val artist: String,
    val album: String?,
    val durationMs: Long,
    val artworkUrl: String?,
    val streamUrl: String?,
    val isExplicit: Boolean,
    val isLiked: Boolean,
    val isDownloaded: Boolean,
    val localFilePath: String? = null,
    val lastPlayedTimestamp: Long = 0L,
    val playCount: Int = 0
)
