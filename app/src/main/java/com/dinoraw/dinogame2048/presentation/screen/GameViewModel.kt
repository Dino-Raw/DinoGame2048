package com.dinoraw.dinogame2048.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinoraw.dinogame2048.domain.model.Cell
import com.dinoraw.dinogame2048.domain.model.Direction
import com.dinoraw.dinogame2048.domain.model.Game
import com.dinoraw.dinogame2048.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private var game: Game,
    private val repository: Repository
) : ViewModel() {
    private var _gridState: MutableStateFlow<List<Cell>> = MutableStateFlow(game.cells.toList())
    val gridState get() = _gridState.asStateFlow()

    private var _scoreState: MutableStateFlow<Int> = MutableStateFlow(game.score.current)
    val scoreState get() = _scoreState.asStateFlow()

    private var _bestScoreState: MutableStateFlow<Int> = MutableStateFlow(game.score.best)
    val bestScoreState get() = _bestScoreState.asStateFlow()

    private var _isOver: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isOver get() = _isOver.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameData().collect { gameFromStore: Game? ->
                if (gameFromStore != null) {
                    if (game.cells != gameFromStore.cells) game = gameFromStore
                    _gridState.update { gameFromStore.cells.toList() }
                    _scoreState.update { gameFromStore.score.current }
                    _bestScoreState.update { gameFromStore.score.best }
                    _isOver.update { gameFromStore.isOver() }
                }
            }
        }
    }

    fun swipe(direction: Direction) {
        game.moveCells(direction)
        saveGameData()
    }

    fun restart() {
        game.restart()
        saveGameData()
    }

    private fun saveGameData(game: Game = this.game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setGameData(game)
        }
    }
}