package com.example.mygaodeweather.logic.network

import com.example.mygaodeweather.MyApplication
import com.example.mygaodeweather.logic.model.ForecastWeather
import com.example.mygaodeweather.logic.model.LivesWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v3/weather/weatherInfo?key=${MyApplication.KEY}&extensions=base&output=JSON")
    fun searchLivesWeather(@Query("city") city: String): Call<LivesWeather>

    @GET("v3/weather/weatherInfo?key=${MyApplication.KEY}&extensions=all&output=JSON")
    fun searchForecastWeather(@Query("city") city: String): Call<ForecastWeather>

}