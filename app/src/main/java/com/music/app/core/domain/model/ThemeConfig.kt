package com.music.app.core.domain.model

enum class ThemeConfig {
    SYSTEM,   // Follow Android system setting
    LIGHT,    // Force Material 3 Light Mode
    DARK,     // Force Material 3 Dark Mode
    DYNAMIC   // Dynamic theme generated from current playing Song Artwork
}
