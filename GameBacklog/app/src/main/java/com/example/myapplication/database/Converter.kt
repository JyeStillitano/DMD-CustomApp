package com.example.myapplication.database

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromSource(platform: Platform) : String {
        return platform.toString()
    }

    @TypeConverter
    fun toSource(platform: String) : Platform {
        return Platform.valueOf(platform)
    }
}