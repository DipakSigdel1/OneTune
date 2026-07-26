package com.music.app.core.data.remote.piped

import com.music.app.core.data.remote.client.KtorClientFactory
import com.music.app.core.data.remote.model.PipedSearchResult
import com.music.app.core.data.remote.model.PipedStreamResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PipedApiClient {
    private val client = KtorClientFactory.create()
    private val baseUrl = "https://pipedapi.kavin.rocks"

    suspend fun searchMusic(query: String): PipedSearchResult {
        return client.get("$baseUrl/search") {
            parameter("q", query)
            parameter("filter", "music_songs")
        }.body()
    }

    suspend fun getAudioStream(videoId: String): PipedStreamResponse {
        return client.get("$baseUrl/streams/$videoId").body()
    }
}
