package com.music.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.music.app.core.data.repository.FeedRepository
import com.music.app.core.domain.model.FeedSection
import com.music.app.core.domain.model.Song
import com.music.app.core.domain.model.TimeOfDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val greeting: String,
        val sections: List<FeedSection>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

class HomeViewModel(
    private val feedRepository: FeedRepository = FeedRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _currentPlayingSong = MutableStateFlow<Song?>(null)
    val currentPlayingSong: StateFlow<Song?> = _currentPlayingSong.asStateFlow()

    init {
        loadHomeFeed()
    }

    fun loadHomeFeed() {
        viewModelScope.launch {
            try {
                feedRepository.getContextualFeed().collect { sections ->
                    val greeting = when (TimeOfDay.getCurrentTimeOfDay()) {
                        TimeOfDay.MORNING -> "Good Morning 🌅"
                        TimeOfDay.DAYTIME -> "Good Afternoon ☀️"
                        TimeOfDay.NIGHT -> "Good Evening 🌙"
                    }
                    _uiState.value = HomeUiState.Success(
                        greeting = greeting,
                        sections = sections
                    )
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.localizedMessage ?: "Failed to load feed")
            }
        }
    }

    fun playSong(song: Song) {
        _currentPlayingSong.value = song
    }
}
