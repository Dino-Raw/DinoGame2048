package com.dinoraw.dinogame2048.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinoraw.dinogame2048.domain.model.Score
import com.dinoraw.dinogame2048.presentation.ui.Color_2048
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
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
        )

        ScoreBox(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f),
            minFontSize = 16.sp,
            label = "SCORE",
            score = score,
        )

        ScoreBox(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f),
            minFontSize = 16.sp,
            label = "BEST",
            score = bestScore,
        )
    }
}