package com.music.app.core.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PipedSearchResult(
    val items: List<PipedItem> = emptyList()
)

@Serializable
data class PipedItem(
    val url: String? = null,
    val title: String? = null,
    val uploaderName: String? = null,
    val thumbnail: String? = null,
    val duration: Long = 0L,
    val type: String? = null
)

@Serializable
data class PipedStreamResponse(
    val title: String? = null,
    val uploader: String? = null,
    val audioStreams: List<PipedAudioStream> = emptyList()
)

@Serializable
data class PipedAudioStream(
    val url: String,
    val format: String,
    val quality: String,
    val bitrate: Long = 0L
)
