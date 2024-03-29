package com.navarayan.weatherexpectancy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navarayan.weatherexpectancy.models.OpenWeatherResult
import com.navarayan.weatherexpectancy.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val liveDataForWeatherResult = MutableLiveData<OpenWeatherResult>()

    fun onStartClicked(cityName: String, unit: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchWeatherByCityName(cityName, unit)
            liveDataForWeatherResult.postValue(result)
        }}

    fun onStartClicked(latitude: Double, longitude: Double, unit: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchWeatherByCoordinate(latitude, longitude, unit)
            liveDataForWeatherResult.postValue(result)
        }
    }
}