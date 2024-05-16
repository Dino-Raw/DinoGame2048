package com.dinoraw.dinogame2048.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
data class Position(
    val row: Int,
    val column: Int,
)