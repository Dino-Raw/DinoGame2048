package com.dinoraw.dinogame2048.presentation.screen.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun Tile(
    text: Int,
    textColor: Color,
    backgroundColor: Color,
    textSize: TextUnit,
    size: Dp,
    animatedScale: Animatable<Float, AnimationVector1D>,
    animatedOffset: Animatable<Offset, AnimationVector2D>,
    modifier: Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .graphicsLayer(
                scaleX = animatedScale.value,
                scaleY = animatedScale.value,
                translationX = animatedOffset.value.x,
                translationY = animatedOffset.value.y,
            )
            .size(size)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4)
            )
    ) {
        Text(
            text = "$text",
            textAlign = TextAlign.Center,
            color = textColor,
            fontSize = textSize,
            fontWeight = FontWeight.Bold
        )
    }
}