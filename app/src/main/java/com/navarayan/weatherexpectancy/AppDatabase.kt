package com.navarayan.weatherexpectancy

import androidx.room.Database
import androidx.room.RoomDatabase
import com.navarayan.weatherexpectancy.models.WeatherSaved
import com.navarayan.weatherexpectancy.repository.local.WeatherDAO

@Database(entities = [WeatherSaved::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun WeatherDAO(): WeatherDAO
}
