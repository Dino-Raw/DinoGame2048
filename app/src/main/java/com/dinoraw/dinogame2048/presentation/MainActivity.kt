package com.dinoraw.dinogame2048.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinoraw.dinogame2048.presentation.screen.GameScreen
import com.dinoraw.dinogame2048.presentation.screen.GameViewModel
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinoGame2048Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen(
                        viewModel = hiltViewModel<GameViewModel>(),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}