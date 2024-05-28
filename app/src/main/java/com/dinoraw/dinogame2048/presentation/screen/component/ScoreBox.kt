package com.dinoraw.dinogame2048.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.presentation.ui.GridBackground
import com.dinoraw.dinogame2048.presentation.ui.TileNum

@Composable
fun ScoreBox(
    modifier: Modifier = Modifier,
    label: String = "SCORE",
    background: Color = Color.Unspecified,
    score: State<Int> = mutableIntStateOf(0),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(
                color = background,
                shape = RoundedCornerShape(4)
            )
            .padding(8.dp)
    ) {
        TextAutoSize(
            text = "${score.value}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = TileNum
        )
        Text(
            text = label,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Normal,
            color = TileNum
        )
    }
}

@Composable
@Preview
fun ScoreBoxPreview() {
    DinoGame2048Theme {
        ScoreBox(
            background = GridBackground
        )
    }
}