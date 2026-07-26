package com.music.app.core.data.repository

import com.music.app.core.data.local.dao.SongDao
import com.music.app.core.data.remote.SongRepositoryImpl
import com.music.app.core.domain.model.FeedSection
import com.music.app.core.domain.model.TimeOfDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FeedRepository(
    private val songRepository: SongRepositoryImpl = SongRepositoryImpl(),
    private val songDao: SongDao? = null
) {
    fun getContextualFeed(): Flow<List<FeedSection>> = flow {
        val sections = mutableListOf<FeedSection>()
        val currentTime = TimeOfDay.getCurrentTimeOfDay()

        // 1. Time-Aware Primary Feed Section
        when (currentTime) {
            TimeOfDay.MORNING -> {
                val morningSongs = songRepository.searchSongs("Morning Workout Energetic Hits")
                sections.add(
                    FeedSection(
                        title = "Morning Motivation",
                        subtitle = "Energetic tracks to kickstart your day",
                        timeContext = TimeOfDay.MORNING,
                        songs = morningSongs
                    )
                )
            }
            TimeOfDay.DAYTIME -> {
                val daySongs = songRepository.searchSongs("Top Chart Hits")
                sections.add(
                    FeedSection(
                        title = "Daytime Preferences",
                        subtitle = "Trending hits tailored for your day",
                        timeContext = TimeOfDay.DAYTIME,
                        songs = daySongs
                    )
                )
            }
            TimeOfDay.NIGHT -> {
                val nightSongs = songRepository.searchSongs("Chill Lofi Relaxing Sleep")
                sections.add(
                    FeedSection(
                        title = "Night Chill & Relax",
                        subtitle = "Low-key songs for evening vibes",
                        timeContext = TimeOfDay.NIGHT,
                        songs = nightSongs
                    )
                )
            }
        }

        // 2. Global Trending Section
        val trendingSongs = songRepository.searchSongs("Popular Songs")
        sections.add(
            FeedSection(
                title = "Trending Now",
                subtitle = "Top played tracks around the world",
                songs = trendingSongs
            )
        )

        emit(sections)
    }
}
