package com.dinoraw.dinogame2048.data.repository

import com.dinoraw.dinogame2048.data.local.LocalDataSource
import com.dinoraw.dinogame2048.domain.model.Game
import com.dinoraw.dinogame2048.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): Repository {
    override fun getGameData(): Flow<Game?> = localDataSource.read()

    override suspend fun setGameData(game: Game) {
        localDataSource.write(game)
    }
}