package com.music.app.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.music.app.core.domain.model.ThemeConfig

class UserPreferencesManager(context: Context) {
    private val prefs: SharedPreferences = 
        context.getSharedPreferences("onetune_prefs", Context.MODE_PRIVATE)

    var themeConfig: ThemeConfig
        get() {
            val name = prefs.getString("key_theme", ThemeConfig.SYSTEM.name)
            return try {
                ThemeConfig.valueOf(name ?: ThemeConfig.SYSTEM.name)
            } catch (e: Exception) {
                ThemeConfig.SYSTEM
            }
        }
        set(value) {
            prefs.edit().putString("key_theme", value.name).apply()
        }

    var cacheTtlDays: Int
        get() = prefs.getInt("key_cache_ttl", 30)
        set(value) = prefs.edit().putInt("key_cache_ttl", value).apply()
}
