package com.music.app.core.domain.model

data class FeedSection(
    val title: String,
    val subtitle: String? = null,
    val timeContext: TimeOfDay? = null,
    val songs: List<Song>
)
