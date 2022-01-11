package com.navarayan.weatherexpectancy.repository.network

import com.navarayan.weatherexpectancy.models.OpenWeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterfaceClass {
    @GET("/")
    suspend fun searchWeatherByCityName(
        @Query("q") cityName:String,
        @Query("appid") appid: String
    ): OpenWeatherResult
}