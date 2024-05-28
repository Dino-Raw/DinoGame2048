package com.dinoraw.dinogame2048.presentation.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinoraw.dinogame2048.presentation.ui.Color_2048
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.presentation.ui.GridBackground
import com.dinoraw.dinogame2048.presentation.ui.TileNum

@Composable
fun GameHeader(
    score: State<Int>,
    bestScore: State<Int>,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
                .background(
                    color = Color_2048,
                    shape = RoundedCornerShape(4)
                ).wrapContentHeight(align = Alignment.CenterVertically),
            text = "2048",
            color = TileNum,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        ScoreBox(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f),
            label = "SCORE",
            score = score,
            background = GridBackground,
        )

        ScoreBox(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f),
            label = "BEST",
            score = bestScore,
            background = GridBackground,
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun GameHeaderPreview() {
    DinoGame2048Theme {
        GameHeader(
            mutableStateOf(100),
            mutableIntStateOf(2048),
            Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}