package com.music.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.music.app.core.data.local.dao.SongDao
import com.music.app.core.data.local.entity.SongEntity

@Database(
    entities = [SongEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}
