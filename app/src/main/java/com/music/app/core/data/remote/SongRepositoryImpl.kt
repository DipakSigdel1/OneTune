package com.music.app.core.data.remote

import com.music.app.core.data.remote.piped.PipedApiClient
import com.music.app.core.domain.model.Song

class SongRepositoryImpl(
    private val pipedApiClient: PipedApiClient = PipedApiClient()
) {
    suspend fun searchSongs(query: String): List<Song> {
        val result = pipedApiClient.searchMusic(query)
        return result.items.mapNotNull { item ->
            val videoId = item.url?.removePrefix("/watch?v=") ?: return@mapNotNull null
            Song(
                id = videoId,
                youtubeId = videoId,
                title = item.title ?: "Unknown Song",
                artist = item.uploaderName ?: "Unknown Artist",
                durationMs = item.duration * 1000L,
                artworkUrl = item.thumbnail
            )
        }
    }

    suspend fun resolveStreamUrl(videoId: String): String? {
        val streamData = pipedApiClient.getAudioStream(videoId)
        // Select highest bitrate Opus or M4A audio stream
        return streamData.audioStreams
            .maxByOrNull { it.bitrate }
            .let { it?.url }
    }
}
