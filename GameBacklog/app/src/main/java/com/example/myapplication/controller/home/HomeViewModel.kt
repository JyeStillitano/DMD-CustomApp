package com.example.myapplication.controller.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Game
import com.example.myapplication.database.GameRepository
import com.example.myapplication.database.GameRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GameRepository

    val games: LiveData<List<Game>>

    init {
        val gameDao = GameRoomDatabase.getDatabase(application, viewModelScope).gameDao()
        repository = GameRepository(gameDao)
        games = repository.allGames
    }

    fun insert(game: Game) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(game)
    }

    fun update(game: Game) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(game)
    }

    fun delete(game: Game) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(game)
    }
}