package com.dinoraw.dinogame2048.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dinoraw.dinogame2048.domain.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalDataSource {
    private val gameKey = stringPreferencesKey("GameDataKey")
    override suspend fun write(game: Game) {
        dataStore.edit { preferences ->
            preferences[gameKey] = Json.encodeToString(game)
        }
    }

    override fun read(): Flow<Game?> = dataStore.data.map { preferences ->
        val gameString = preferences[gameKey]
        if (gameString != null) Json.decodeFromString<Game>(gameString)
        else null//Game.default
    }
}