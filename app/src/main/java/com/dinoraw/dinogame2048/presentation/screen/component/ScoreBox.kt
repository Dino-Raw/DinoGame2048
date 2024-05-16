package com.dinoraw.dinogame2048.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinoraw.dinogame2048.domain.model.Score
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.presentation.ui.GridBackground
import com.theapache64.rebugger.Rebugger
import com.dinoraw.dinogame2048.presentation.ui.TileNum

@Composable
fun ScoreBox(
    modifier: Modifier = Modifier,
    label: String = "SCORE",
    score: State<Int> = mutableIntStateOf(0),
    minFontSize: TextUnit = 16.sp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(
                color = GridBackground,
                shape = RoundedCornerShape(4)
            )
            .padding(8.dp)
    ) {
        Text(
            text = "${score.value}",
            fontSize = minFontSize * 2,
            fontWeight = FontWeight.Bold,
            color = TileNum
        )
        Text(
            text = label,
            fontSize = minFontSize,
            fontWeight = FontWeight.Light,
            color = TileNum
        )
    }
}

@Composable
@Preview
fun ScoreBoxPreview() {
    DinoGame2048Theme {
        ScoreBox()
    }
}