package com.dinoraw.dinogame2048.domain.model

import com.dinoraw.dinogame2048.util.Const.GRID_SIZE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Grid(
    @SerialName("cells")
    private var _cells: MutableList<Cell> = mutableListOf(),
) {

    companion object {
        val default = Grid().also {
            it.addCell(2)
        }
    }

    val cells: List<Cell> get() = _cells

    fun clear() {
        _cells.clear()
    }

    fun prepareForMove(direction: Direction, ) = when (direction) {
        Direction.UP -> {
            _cells.sortBy { it.position.row }
            MovementParameters(-1, 0) { it.row < 0 }
        }
        Direction.DOWN -> {
            _cells.sortByDescending { it.position.row }
            MovementParameters(1, 0) { it.row == GRID_SIZE  }
        }
        Direction.LEFT -> {
            _cells.sortBy { it.position.column }
            MovementParameters(0, -1) { it.column < 0  }
        }
        Direction.RIGHT -> {
            _cells.sortByDescending { it.position.column }
            MovementParameters(0, 1) { it.column == GRID_SIZE  }
        }
    }

    fun canMove(
        operatorRow: Int,
        operatorColumn: Int,
        positionOutsideTheGrid: (Position) -> Boolean,
    ) : Boolean {
        for (cellNum in 0 until _cells.size) {
            val currentCell = _cells[cellNum].copy()
            val nextPosition = _cells[cellNum].getNextPosition(operatorRow, operatorColumn)
            val nextCell = findCellByPosition(nextPosition)

            if (positionOutsideTheGrid(nextPosition)) continue
            if (currentCell.differentNumbers(nextCell)) continue
            if (cellsReadyToMerge(nextPosition)) continue
            return true
        }
        return false
    }

    fun move(
        operatorRow: Int,
        operatorColumn: Int,
        positionOutsideTheGrid: (Position) -> Boolean,
    ): Boolean {
        var isMove = false
        for (cellNum in 0 until _cells.size) {
            var currentCell = _cells[cellNum].copy()
            var nextPosition = _cells[cellNum].getNextPosition(operatorRow, operatorColumn)
            var nextCell = findCellByPosition(nextPosition)

            while(true) {
                if (positionOutsideTheGrid(nextPosition)) break
                if (currentCell.differentNumbers(nextCell)) break
                if (cellsReadyToMerge(nextPosition)) break

                currentCell = currentCell.copy(
                    //previousPosition = currentCell.position,
                    position = nextPosition,
                )
                nextPosition = nextPosition.copy(
                    row = nextPosition.row + operatorRow,
                    column = nextPosition.column + operatorColumn
                )
                nextCell = findCellByPosition(nextPosition)
                isMove = true
            }
            if (isMove) _cells[cellNum] = currentCell.copy(previousPosition = _cells[cellNum].position,)
        }
        return isMove
    }

    fun removeCellsThatMayMerge() {
        _cells.groupBy { it.position }.forEach { (_, list) ->
            if (list.size > 1) _cells.removeAll { cell -> cell.canMerged(list.first()) }
        }
    }

    fun addMergedCells(): Int {
        var sumNumbersMergedCells = 0
        _cells.groupBy { it.position }.forEach { (_, list) ->
           if (list.size > 1) {
               val cell = list.first().getMerged()
               _cells.add(cell)
               sumNumbersMergedCells += cell.number
           }
       }
        return sumNumbersMergedCells
    }

    fun addCell(num: Int = 1) {
        for (i in 0 until num) {
            val position = getRandomEmptyPosition() ?: break
            val number = if ((0 until 100).random() < 90) 2 else 4
            _cells.add(Cell(number = number, position = position))
        }
    }

    private fun cellsReadyToMerge(position: Position) =
        _cells.filter { it.position == position }.size > 1

    private fun findCellByPosition(position: Position) =
        _cells.find { it.position == position }

    private fun getRandomEmptyPosition(): Position? {
        val randomIndex = (0 until GRID_SIZE * GRID_SIZE).random()

        fun findEmptyPosition(
            index: Int,
            operator: Int,
            condition: (Int) -> Boolean
        ): Position? {
            var nextIndex = index
            while (condition(nextIndex)) {
                val pos = Position(row = nextIndex / GRID_SIZE, column = nextIndex % GRID_SIZE)
                if (findCellByPosition(pos) == null) return pos
                nextIndex += operator
            }
            return null
        }

        return findEmptyPosition(
            index = randomIndex,
            operator = 1,
            condition = { it < GRID_SIZE * GRID_SIZE }
        ) ?: findEmptyPosition(
            index = randomIndex,
            operator = -1,
            condition = { it >= 0 }
        )
    }
}