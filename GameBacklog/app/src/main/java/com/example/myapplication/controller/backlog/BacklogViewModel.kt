package com.example.myapplication.controller.backlog

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.database.Game
import com.example.myapplication.database.GameRepository
import com.example.myapplication.database.GameRoomDatabase

class BacklogViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GameRepository

    val games: LiveData<List<Game>>

    init {
        val gameDao = GameRoomDatabase.getDatabase(application, viewModelScope).gameDao()
        repository = GameRepository(gameDao)
        games = repository.backlogGames
    }
}