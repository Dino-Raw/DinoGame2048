package com.dinoraw.dinogame2048.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class MovementParameters(
    val operatorRow: Int,
    val operatorColumn: Int,
    val positionOutsideTheGrid: (Position) -> Boolean,
)
