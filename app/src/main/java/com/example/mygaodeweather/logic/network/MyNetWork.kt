package com.example.mygaodeweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object MyNetWork {

    private val weatherService = ServiceCreator.create<WeatherService>()
    suspend fun searchLivesWeather(city: String) = weatherService.searchLivesWeather(city).await()
    suspend fun searchForecastWeather(city: String) =
        weatherService.searchForecastWeather(city).await()

    private suspend fun <T> Call<T>.await(): T = suspendCoroutine {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) it.resume(body)
                else it.resumeWithException(RuntimeException("response is null!"))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                it.resumeWithException(t)
            }

        })
    }
}