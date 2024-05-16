package com.dinoraw.dinogame2048.presentation.screen.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinoraw.dinogame2048.domain.model.Cell
import com.dinoraw.dinogame2048.domain.model.Position
import com.dinoraw.dinogame2048.presentation.model.ColorTile
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme
import com.dinoraw.dinogame2048.presentation.ui.GridBackground
import com.dinoraw.dinogame2048.presentation.ui.GridTile
import com.dinoraw.dinogame2048.util.Const.GRID_SIZE
import kotlinx.coroutines.launch
import kotlin.math.min

@SuppressLint("UnrememberedMutableState", "MutableCollectionMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameGridPreview() {
    DinoGame2048Theme {
        GameGrid(mutableStateOf(mutableListOf(Cell(2, Position(0, 0)))))
    }
}

@Composable
fun GameGrid(
    grid: State<List<Cell>>,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .background(
                color = GridBackground,
                shape = RoundedCornerShape(2)
            )
            .padding(all = 8.dp)
            .aspectRatio(1f)
    ) {
        val gridSizePx = with(LocalDensity.current) { min(maxWidth.toPx(), maxHeight.toPx()) }
        val tileMarginPx = with(LocalDensity.current) { 8.dp.toPx() }
        val tileSizePx = ((gridSizePx - tileMarginPx * (GRID_SIZE - 1)) / GRID_SIZE).coerceAtLeast(0f)
        val tileOffsetPx = tileSizePx + tileMarginPx

        Box (
            modifier.drawBehind {
                for (row in 0 until GRID_SIZE) {
                    for (column in 0 until GRID_SIZE) {
                        drawRoundRect(
                            color = GridTile,
                            topLeft = Offset(column * tileOffsetPx, row * tileOffsetPx),
                            size = Size(tileSizePx, tileSizePx),
                            cornerRadius = CornerRadius(4.dp.toPx()),
                        )
                    }
                }
            }
        ) {
            for (cell in grid.value) {
                val fromScale = if (cell.previousPosition == null) 0f else 1f
                val toOffset = Offset(
                    cell.position.column * tileOffsetPx,
                    cell.position.row * tileOffsetPx
                )
                val fromOffset = cell.previousPosition?.let { position ->
                    Offset(
                        position.column * tileOffsetPx,
                        position.row * tileOffsetPx
                    )
                } ?: toOffset

                key(cell) {
                    val animatedScale = remember { Animatable(fromScale) }
                    val animatedOffset = remember { Animatable(fromOffset, Offset.VectorConverter) }
                    val colorTile = ColorTile(cell.number)
                    Tile(
                        text = cell.number,
                        textColor = colorTile.number,
                        backgroundColor = colorTile.background,
                        textSize = (tileSizePx / LocalDensity.current.density / 2).sp,
                        size = (tileSizePx / LocalDensity.current.density).dp,
                        animatedScale = animatedScale,
                        animatedOffset = animatedOffset,
                        modifier = Modifier
                    )
                    LaunchedEffect(cell.position) {
                        launch { animatedScale.animateTo(1f, tween(200, 50)) }
                        launch { animatedOffset.animateTo(toOffset, tween(100)) }
                    }
                }
            }
        }
    }
}