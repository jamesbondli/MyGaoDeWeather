package com.example.mygaodeweather.logic.model

import com.google.gson.annotations.SerializedName

data class Weather(val livesWeather: LivesWeather, val forecastWeather: ForecastWeather)

data class LivesWeather(val status: String, val lives: Lives) {
    data class Lives(
        val province: String,
        val city: String,
        @SerializedName("adcode") val adCode: String,
        val weather: String,
        val temperature: String,
        @SerializedName("winddirection") val windDirection: String,
        @SerializedName("windpower") val windPower: String,
        val humidity: String,
        @SerializedName("reporttime") val reportTime: String
    )
}

data class ForecastWeather(val status: String, val forecast: Forecast) {
    data class Forecast(
        val city: String,
        @SerializedName("adcode") val adCode: String,
        val province: String,
        @SerializedName("reporttime") val reportTime: String,
        val cast: List<Cast>
    )

    data class Cast(
        val date: String,
        val week: String,
        @SerializedName("dayweather") val dayWeather: String,
        @SerializedName("nightweather") val nightWeather: String,
        @SerializedName("daytemp") val dayTemp: String,
        @SerializedName("nighttemp") val nightTemp: String,
        @SerializedName("daywind") val dayWind: String,
        @SerializedName("nightwind") val nightWind: String,
        @SerializedName("daypower") val dayPower: String,
        @SerializedName("nightpower") val nightPower: String
    )
}