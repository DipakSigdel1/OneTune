package com.music.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.music.app.core.ui.theme.OneTuneTheme
import com.music.app.feature.home.HomeScreen
import com.music.app.feature.home.HomeViewModel
import com.music.app.feature.player.MiniPlayer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneTuneTheme {
                val homeViewModel: HomeViewModel = viewModel()
                val currentSong by homeViewModel.currentPlayingSong.collectAsState()
                var isPlaying by remember { mutableStateOf(true) }

                Box(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(
                        viewModel = homeViewModel,
                        onSongClick = { song ->
                            homeViewModel.playSong(song)
                            isPlaying = true
                        }
                    )

                    currentSong?.let { song ->
                        MiniPlayer(
                            song = song,
                            isPlaying = isPlaying,
                            onPlayPauseClick = { isPlaying = !isPlaying },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }
}
