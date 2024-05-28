package com.dinoraw.dinogame2048.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Immutable
class Score(
    @SerialName("current")
    private var _current: Int = 0,
    @SerialName("best")
    private var _best: Int = 0,
) {
    val current: Int get() = _current
    val best: Int get() = _best

    fun add(additionalScore: Int) {
        _current += additionalScore
        _best = if (_current > _best) _current else _best
    }

    fun clear() {
        _current = 0
    }
}