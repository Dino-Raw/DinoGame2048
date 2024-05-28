package com.dinoraw.dinogame2048.data.local

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore("DataStore")