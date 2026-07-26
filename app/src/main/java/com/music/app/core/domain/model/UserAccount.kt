package com.music.app.core.domain.model

data class UserAccount(
    val googleAccountId: String? = null,
    val googleEmail: String? = null,
    val isYouTubeConnected: Boolean = false,
    val spotifyAccountId: String? = null,
    val isSpotifyConnected: Boolean = false
)
