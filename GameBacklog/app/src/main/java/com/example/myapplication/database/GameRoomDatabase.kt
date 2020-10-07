package com.example.myapplication.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Game::class), version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class GameRoomDatabase : RoomDatabase() {

    // Getter for Game data access objects.
    abstract fun gameDao(): GameDao

    private class GameDatabaseCallback(private val scope: CoroutineScope)
        : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    // Populate database
                    // populateDatabase(database.gameDao())
                }
            }
        }
    }

    // Companion so there isn't multiple instances accessing the DB at once.
    companion object {
        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        // if Database exists, return it. If not, create instance and return it.
        fun getDatabase(context: Context, scope: CoroutineScope): GameRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDatabase::class.java,
                    "game_database"
                ).addCallback(
                    GameDatabaseCallback(
                        scope
                    )
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }

    }

}