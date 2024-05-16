package com.dinoraw.dinogame2048.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dinoraw.dinogame2048.domain.model.Direction
import com.dinoraw.dinogame2048.presentation.screen.component.GameHeader
import com.dinoraw.dinogame2048.presentation.screen.component.GameGrid
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.util.Const.SWIPE_OFFSET
import com.theapache64.rebugger.Rebugger
import kotlin.math.abs

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel<GameViewModel>(),
    offset: Int = SWIPE_OFFSET,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    val grid = viewModel.gridState.collectAsStateWithLifecycle()
    val score = viewModel.scoreState.collectAsStateWithLifecycle()
    val bestScore = viewModel.bestScoreState.collectAsStateWithLifecycle()
    var direction: Direction? = null
    Box(
        modifier = modifier
           // .fillMaxSize()
            .pointerInput(null) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x, y) = dragAmount
                        if (abs(x) > abs(y))
                            when {
                                x > offset -> {
                                    direction = Direction.RIGHT
                                }

                                x < -offset -> {
                                    direction = Direction.LEFT
                                }
                            }
                        else
                            when {
                                y > offset -> {
                                    direction = Direction.DOWN
                                }

                                y < -offset -> {
                                    direction = Direction.UP
                                }
                            }
                    },
                    onDragEnd = {
                        direction?.let { viewModel.swipe(it) }
                    }
                )
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            GameHeader(
                score = score,
                bestScore = bestScore,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            GameGrid(
                grid = grid,
                modifier = Modifier
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GamePreview() {
    DinoGame2048Theme {
        GameScreen()
    }
}