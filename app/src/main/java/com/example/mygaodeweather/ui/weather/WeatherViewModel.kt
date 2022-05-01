package com.example.mygaodeweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mygaodeweather.logic.Repository

class WeatherViewModel : ViewModel() {

    private val searchWeatherLivedata = MutableLiveData<String>()

    val weatherLiveData =
        Transformations.switchMap(searchWeatherLivedata) { Repository.refreshWeather(it) }

    fun refreshWeather(city: String) {
        searchWeatherLivedata.value = city
    }
}