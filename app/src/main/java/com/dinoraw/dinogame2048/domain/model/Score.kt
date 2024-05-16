package com.dinoraw.dinogame2048.domain.model

import androidx.compose.runtime.Immutable

@Immutable
class Score(
    private var _current: Int = 0,
    private var _best: Int = 0,
) {
    val current: Int get() = _current
    val best: Int get() = _best

    fun add(additionalScore: Int) {
        val newCurrentScore = current + additionalScore
        _current = newCurrentScore
        _best = if (newCurrentScore > _best) newCurrentScore else _best
    }

    fun clear() {
        _current = 0
    }
}