package com.dinoraw.dinogame2048.domain.di

import com.dinoraw.dinogame2048.domain.model.Game
import com.dinoraw.dinogame2048.domain.model.Grid
import com.dinoraw.dinogame2048.domain.model.Score
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideScore() = Score(0, 0)

    @Provides
    @Singleton
    fun provideGrid() = Grid.default

    @Provides
    @Singleton
    fun provideGame(grid: Grid, score: Score) = Game(grid, score)
}