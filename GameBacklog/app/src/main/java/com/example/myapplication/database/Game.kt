package com.example.myapplication.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Enum for Platform selection.
enum class Platform {
    PC, Sega, Atari, Arcade,
    NES, SNES, GameCube, Nintendo64, Wii, WiiU, NintendoSwitch, GameBoy, NintendoDS,
    PlayStation1, PlayStation2, PlayStation3, PlayStation4, PlayStation5, PSP, PlayStationVita,
    XBox, XBox360, XBoxOne, XBoxOneX
}
// Entity Class to allow simple CRUD to the Room Database
@Parcelize
@Entity(tableName = "game_table")
data class Game(@PrimaryKey val name: String, val platform: Platform,
                val desc: String, val completion: Float) : Parcelable