package com.dinoraw.dinogame2048.domain.repository

interface Repository {
    fun insert()
    fun update()
    fun remove()
    fun load()
}