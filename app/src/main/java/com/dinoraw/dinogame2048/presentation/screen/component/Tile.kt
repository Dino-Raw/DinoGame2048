package com.dinoraw.dinogame2048.presentation.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme

@Composable
fun Tile(
    text: Int,
    textColor: Color,
    modifier: Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        TextAutoSize(
            text = "$text",
            color = textColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@SuppressLint("UnrememberedAnimatable")
@Preview
@Composable
private fun TilePreview() {
    DinoGame2048Theme {
        Tile(
            text = 2048,
            textColor = Color.Blue,
            modifier = Modifier
        )
    }
}