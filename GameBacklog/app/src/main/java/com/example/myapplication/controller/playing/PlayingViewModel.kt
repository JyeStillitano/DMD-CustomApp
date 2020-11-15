package com.example.myapplication.controller.playing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Game
import com.example.myapplication.database.GameRepository
import com.example.myapplication.database.GameRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GameRepository

    val games: LiveData<List<Game>>

    init {
        val gameDao = GameRoomDatabase.getDatabase(application, viewModelScope).gameDao()
        repository = GameRepository(gameDao)
        games = repository.playingGames
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