package com.dinoraw.dinogame2048.presentation.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.dinoraw.dinogame2048.presentation.ui.Color_1024
import com.dinoraw.dinogame2048.presentation.ui.Color_128
import com.dinoraw.dinogame2048.presentation.ui.Color_16
import com.dinoraw.dinogame2048.presentation.ui.Color_2
import com.dinoraw.dinogame2048.presentation.ui.Color_2048
import com.dinoraw.dinogame2048.presentation.ui.Color_256
import com.dinoraw.dinogame2048.presentation.ui.Color_32
import com.dinoraw.dinogame2048.presentation.ui.Color_4
import com.dinoraw.dinogame2048.presentation.ui.Color_512
import com.dinoraw.dinogame2048.presentation.ui.Color_64
import com.dinoraw.dinogame2048.presentation.ui.Color_8
import com.dinoraw.dinogame2048.presentation.ui.TileNum
import com.dinoraw.dinogame2048.presentation.ui.TileNumDark

@Stable
class ColorTile(value: Int) {
    val background = getColorForValue(value)
    val number = if (value < 8) TileNumDark else TileNum

    private fun getColorForValue(value: Int): Color {
        return when (value) {
            2 -> Color_2
            4 -> Color_4
            8 -> Color_8
            16 -> Color_16
            32 -> Color_32
            64 -> Color_64
            128 -> Color_128
            256 -> Color_256
            512 -> Color_512
            1024 -> Color_1024
            2048 -> Color_2048
            else -> Color.Black
        }
    }
}