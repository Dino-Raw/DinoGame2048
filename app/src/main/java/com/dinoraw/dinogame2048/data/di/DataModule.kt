package com.dinoraw.dinogame2048.data.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.dinoraw.dinogame2048.data.local.DataBase
import com.dinoraw.dinogame2048.domain.model.Cell
import com.dinoraw.dinogame2048.domain.model.Game
import com.dinoraw.dinogame2048.domain.model.Grid
import com.dinoraw.dinogame2048.domain.model.Score
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideScore() = Score(0, 44)

    @Provides
    @Singleton
    fun provideGrid() = Grid(_cells = mutableListOf())

    @Provides
    @Singleton
    fun provideGame(grid: Grid, score: Score) = Game(grid, score)

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DataBase::class.java,"${context.packageName}.DB").build()
}