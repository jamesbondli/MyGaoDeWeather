package com.example.mygaodeweather.logic

import androidx.lifecycle.liveData
import com.example.mygaodeweather.logic.model.Weather
import com.example.mygaodeweather.logic.network.MyNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun refreshWeather(city: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredLivesWeather = async { MyNetWork.searchLivesWeather(city) }
            val deferredForecastWeather = async { MyNetWork.searchForecastWeather(city) }
            val livesWeather = deferredLivesWeather.await()
            val forecastWeather = deferredForecastWeather.await()
            if (livesWeather.status == "1" && forecastWeather.status == "1") {
                val weather = Weather(livesWeather, forecastWeather)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "lives' status is ${livesWeather.status}," +
                                "forecast's status is ${forecastWeather.status}"
                    )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}