package com.example.myapplication.database

import androidx.room.TypeConverter

class Converter {
    // Needed for converting Platform enum into String and back.
    @TypeConverter
    fun fromSource(platform: Platform) : String {
        return platform.toString()
    }

    @TypeConverter
    fun toSource(platform: String) : Platform {
        return Platform.valueOf(platform)
    }
}