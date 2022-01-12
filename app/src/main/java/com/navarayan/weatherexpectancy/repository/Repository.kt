package com.navarayan.weatherexpectancy.repository

import com.navarayan.weatherexpectancy.AppDatabase
import com.navarayan.weatherexpectancy.models.OpenWeatherResult
import com.navarayan.weatherexpectancy.repository.network.RetrofitInterfaceClass
import javax.inject.Inject

const val APP_ID = "39b64965c9ff0b522ff5e118f12ee864"

class Repository @Inject constructor(
    private val db: AppDatabase,
    private val network: RetrofitInterfaceClass
) {
    suspend fun searchWeatherByCityName(cityName: String): OpenWeatherResult {
        return network.searchWeatherByCityName(cityName, APP_ID, "metric")
    }

    suspend fun searchWeatherByCoordinate(latitude: Double, longitude: Double): OpenWeatherResult {
        return network.searchWeatherByCoordinate(latitude, longitude, APP_ID, "metric")
    }

}