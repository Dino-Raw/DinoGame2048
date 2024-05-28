package com.dinoraw.dinogame2048.data.local

import com.dinoraw.dinogame2048.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun write(game: Game)
    fun read(): Flow<Game?>
}