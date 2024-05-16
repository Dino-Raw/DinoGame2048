package com.dinoraw.dinogame2048.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dinoraw.dinogame2048.domain.model.Cell
import com.dinoraw.dinogame2048.domain.model.Direction
import com.dinoraw.dinogame2048.domain.model.Game
import com.dinoraw.dinogame2048.domain.model.Grid
import com.dinoraw.dinogame2048.domain.model.Score
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val game: Game,
): ViewModel() {
    private var _gridState: MutableStateFlow<List<Cell>> = MutableStateFlow(game.cells.toList())
    val gridState get() = _gridState.asStateFlow()

    private var _scoreState: MutableStateFlow<Int> = MutableStateFlow(game.score.current)
    val scoreState get() = _scoreState.asStateFlow()

    private var _bestScoreState: MutableStateFlow<Int> = MutableStateFlow(game.score.best)
    val bestScoreState get() = _bestScoreState.asStateFlow()

    fun swipe(direction: Direction) {
        game.moveCells(direction)
        update()
        if (game.isOver()) Log.i("GAME IS OVER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    private fun update() {
        _gridState.update { game.cells.toList() }
        _scoreState.update { game.score.current }
        _bestScoreState.update { game.score.best }
    }
}