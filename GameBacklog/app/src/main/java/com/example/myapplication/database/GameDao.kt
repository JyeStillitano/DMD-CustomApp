package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

// Data access object to Create, Read, Update and Delete games from the database.
@Dao
interface GameDao {

    // CREATE. Insert new Game object to DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    // READ. Get a list of all uncompleted Games from DB
    @Query("SELECT * from game_table WHERE completion < '100.0' ORDER BY name ASC")
    fun getGames(): LiveData<List<Game>>

    // READ. Get a list of all completed Games from DB
    @Query("SELECT * from game_table WHERE completion='100.0' ORDER BY name ASC")
    fun getCompletedGames(): LiveData<List<Game>>

    // UPDATE. Update an existing Game entity object in the DB
    @Update
    fun updateGame(game: Game)

    // DELETE. Delete a Game record from the DB
    @Delete
    fun deleteGame(game: Game)
}