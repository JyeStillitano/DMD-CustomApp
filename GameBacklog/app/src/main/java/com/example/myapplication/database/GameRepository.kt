package com.example.myapplication.database

import androidx.lifecycle.LiveData

// Handles access to the database and LiveData lists. Wraps around the gameDao functions.
class GameRepository(private val gameDao: GameDao) {
    val backlogGames : LiveData<List<Game>> = gameDao.getBacklogGames()     // Backlog List
    val playingGames: LiveData<List<Game>> = gameDao.getPlayingGames()      // Playing List
    val completedGames : LiveData<List<Game>> = gameDao.getCompletedGames() // Completed List

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