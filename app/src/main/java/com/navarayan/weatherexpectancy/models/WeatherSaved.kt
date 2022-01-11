package com.navarayan.weatherexpectancy.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherSaved (
    @PrimaryKey val city_Name: String,
    val temp: Double,
    val feels_like: Double,
    val temp_max: Double,
    val temp_min: Double,
    val sunrise: Int,
    val sunset: Int,
    val wind_speed: Double
)