package com.dinoraw.dinogame2048.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.presentation.ui.GridBackground

@Composable
fun GameDialog(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { },
) {
    Box(
        modifier = modifier
            .alpha(0.5f)
            .background(color = GridBackground, shape = RoundedCornerShape(2))
            .aspectRatio(1f),
        contentAlignment = Alignment.Center,

    ) { content() }
}

@Preview(showSystemUi = true)
@Composable
private fun GameDialogPreview() {
    DinoGame2048Theme {
        GameDialog {
            Text(
                text = "Game is over!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }
}