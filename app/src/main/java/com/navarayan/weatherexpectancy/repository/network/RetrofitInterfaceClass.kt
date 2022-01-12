package com.navarayan.weatherexpectancy.repository.network

import com.navarayan.weatherexpectancy.models.OpenWeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterfaceClass {
    @GET("data/2.5/weather")
    suspend fun searchWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): OpenWeatherResult

    @GET("data/2.5/weather")
    suspend fun searchWeatherByCoordinate(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): OpenWeatherResult
}