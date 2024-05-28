package com.dinoraw.dinogame2048.domain.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Serializable
@Stable
data class Position(
    val row: Int,
    val column: Int,
)