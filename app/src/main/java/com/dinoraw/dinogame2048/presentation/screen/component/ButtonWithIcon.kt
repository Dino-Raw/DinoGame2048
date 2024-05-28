package com.dinoraw.dinogame2048.presentation.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinoraw.dinogame2048.R
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme

@Composable
fun ButtonWithIcon(
    icon: Int,
    onClick: () -> Unit = { },
    background: Color = Color.Unspecified,
    contentDescription: String? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = background, shape = RoundedCornerShape(8))
            .clickable { onClick() }
            .padding(4.dp),
    ) {
        Image(painter = painterResource(id = icon), contentDescription = contentDescription)
    }
}

@Preview
@Composable
private fun ButtonWithIconPreview() {
    DinoGame2048Theme {
        ButtonWithIcon(icon = R.drawable.refresh_24)
    }
}