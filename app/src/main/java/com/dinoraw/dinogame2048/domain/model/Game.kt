package com.dinoraw.dinogame2048.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Game (
    @SerialName("grid")
    private var _grid: Grid,
    @SerialName("score")
    private var _score: Score,
) {
    val cells: List<Cell> get() = _grid.cells
    val score: Score get() = _score

    fun restart() {
        _grid.clear()
        _score.clear()
        _grid.addCell(2)
    }

    fun moveCells(direction: Direction) {
        _grid.removeCellsThatMayMerge()
        val parameters = _grid.prepareForMove(direction)
        val moved = _grid.move(
            parameters.operatorRow,
            parameters.operatorColumn,
            parameters.positionOutsideTheGrid
        )
        if (moved) {
            val sumNumbersMergedCells: Int = _grid.addMergedCells()
            _score.add(sumNumbersMergedCells)
            _grid.addCell()
        }
    }

    fun isOver(): Boolean {
        Direction.entries.forEach { direction ->
            val parameters = _grid.prepareForMove(direction)
            val canMove = _grid.canMove(
                parameters.operatorRow,
                parameters.operatorColumn,
                parameters.positionOutsideTheGrid
            )
            if (canMove) return false
        }
        return true
    }
}