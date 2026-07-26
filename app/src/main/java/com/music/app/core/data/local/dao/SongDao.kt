package com.music.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.music.app.core.data.local.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM songs WHERE isLiked = 1 ORDER BY lastPlayedTimestamp DESC")
    fun getLikedSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE isDownloaded = 1")
    fun getDownloadedSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs ORDER BY lastPlayedTimestamp DESC LIMIT 50")
    fun getListeningHistory(): Flow<List<SongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(song: SongEntity)

    @Query("UPDATE songs SET isLiked = :isLiked WHERE id = :songId")
    suspend fun updateLikeStatus(songId: String, isLiked: Boolean)

    @Query("DELETE FROM songs WHERE id = :songId")
    suspend fun deleteSong(songId: String)
}
