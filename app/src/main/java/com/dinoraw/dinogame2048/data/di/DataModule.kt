package com.dinoraw.dinogame2048.data.di

import android.content.Context
import com.dinoraw.dinogame2048.data.local.LocalDataSource
import com.dinoraw.dinogame2048.data.local.LocalDataSourceImpl
import com.dinoraw.dinogame2048.data.local.dataStore
import com.dinoraw.dinogame2048.data.repository.RepositoryImpl
import com.dinoraw.dinogame2048.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    companion object {
        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context) = context.dataStore
    }
}