package com.navarayan.weatherexpectancy

import android.content.Context
import androidx.room.Room
import com.navarayan.weatherexpectancy.repository.network.RetrofitInterfaceClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    fun roomProvider(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "WeatherSaved").build()
    }

    @Provides
    fun retrofitProvider(): RetrofitInterfaceClass {
        val baseURL = "https://api.openweathermap.org/"

        val retrofit =
            Retrofit.Builder().baseUrl(baseURL).addConverterFactory(MoshiConverterFactory.create())
                .build()

        return retrofit.create(RetrofitInterfaceClass::class.java)
    }
}