package com.dinoraw.dinogame2048.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinoraw.dinogame2048.domain.model.Cell
import com.dinoraw.dinogame2048.domain.model.Position
import com.dinoraw.dinogame2048.presentation.screen.component.DescriptionAndButtonRestart
import com.dinoraw.dinogame2048.presentation.screen.component.GameGrid
import com.dinoraw.dinogame2048.presentation.screen.component.GameHeader
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.presentation.ui.TileNum

@Composable
fun GameContent(
    score: State<Int>,
    bestScore: State<Int>,
    grid: State<List<Cell>>,
    isOver: State<Boolean>,
    restart: () -> Unit = { },
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GameHeader(
                score = score,
                bestScore = bestScore,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.padding(vertical = 4.dp)
            )

            DescriptionAndButtonRestart(
                isOver = isOver,
                restart = { restart() },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.padding(vertical = 4.dp)
            )

            GameGrid(
                grid = grid,
                isOver = isOver,
                modifier = Modifier
            )
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun GameContentPreview() {
    DinoGame2048Theme {
        GameContent(
            modifier = Modifier
                .fillMaxSize()
                .background(color = TileNum)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            score = mutableIntStateOf(0),
            bestScore = mutableIntStateOf(12345),
            isOver = mutableStateOf(false),
            grid = mutableStateOf(
                listOf(
                    Cell(2, Position(0, 0)),
                    Cell(4, Position(1, 0)),
                    Cell(8, Position(2, 0)),
                    Cell(16, Position(3, 0)),
                )
            ),
        )
    }
}