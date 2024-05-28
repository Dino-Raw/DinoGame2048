package com.dinoraw.dinogame2048.domain.repository

import com.dinoraw.dinogame2048.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getGameData(): Flow<Game?>
    suspend fun setGameData(game: Game)
}