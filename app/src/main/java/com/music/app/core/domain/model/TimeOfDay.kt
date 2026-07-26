package com.music.app.core.domain.model

import java.util.Calendar

enum class TimeOfDay {
    MORNING,  // 05:00 - 11:59 (Energetic, Workout, Morning vibes)
    DAYTIME,  // 12:00 - 17:59 (User preferences, Top played, Daily mix)
    NIGHT;    // 18:00 - 04:59 (Relaxing, Sleep, Chill, Low-key party)

    companion object {
        fun getCurrentTimeOfDay(): TimeOfDay {
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            return when (hour) {
                in 5..11 -> MORNING
                in 12..17 -> DAYTIME
                else -> NIGHT
            }
        }
    }
}
