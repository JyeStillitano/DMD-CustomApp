package com.example.myapplication.database

import androidx.lifecycle.LiveData

class GameRepository(private val gameDao: GameDao) {
    val allGames: LiveData<List<Game>> = gameDao.getGames()
    val completedGames : LiveData<List<Game>> = gameDao.getCompletedGames()

    suspend fun insert(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun update(game: Game) {
        gameDao.updateGame(game)
    }

    suspend fun delete(game: Game) {
        gameDao.deleteGame(game)
    }
}