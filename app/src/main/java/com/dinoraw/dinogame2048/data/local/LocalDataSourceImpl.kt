package com.dinoraw.dinogame2048.data.local

import android.content.Context
import android.content.SharedPreferences
import com.dinoraw.dinogame2048.domain.model.Cell
import com.dinoraw.dinogame2048.domain.model.Score
import dagger.hilt.android.qualifiers.ApplicationContext

class LocalDataSourceImpl(
    private val sp: SharedPreferences
) {

}