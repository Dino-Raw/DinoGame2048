package com.dinoraw.dinogame2048.presentation.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinoraw.dinogame2048.R
import com.dinoraw.dinogame2048.presentation.ui.Color_2048
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.presentation.ui.GridBackground

@Composable
fun DescriptionAndButtonRestart(
    isOver: State<Boolean>,
    restart: () -> Unit = { },
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextAutoSize(
                text = buildAnnotatedString {
                    append("Join the numbers and get to the ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("2048 tile! ")
                    }
                },
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            ButtonWithIcon(
                onClick = { restart() },
                icon = R.drawable.refresh_24,
                contentDescription = "Restart game button",
                background = if (isOver.value) Color_2048 else GridBackground,
                modifier = Modifier.weight(0.1f).aspectRatio(1f)
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun DescriptionAndButtonRestartPreview() {
    DinoGame2048Theme {
        DescriptionAndButtonRestart(mutableStateOf(false), { })
    }
}