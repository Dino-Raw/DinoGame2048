package com.dinoraw.dinogame2048.domain.model

import androidx.compose.runtime.Stable

@Stable
data class Cell(
    val number: Int,
    val position: Position,
    val previousPosition: Position? = null,
) {
    fun differentNumbers(cell: Cell?) = cell!= null && number != cell.number
    fun canMerged(cell: Cell) = position == cell.position && number == cell.number
    fun getMerged() = copy(number = number * 2, previousPosition = null)
    fun getNextPosition(operatorRow: Int, operatorColumn: Int) = position.copy(
        row = position.row + operatorRow,
        column = position.column + operatorColumn
    )
}
