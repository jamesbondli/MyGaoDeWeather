package com.example.mygaodeweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val KEY = "9ad0e7e4d7f9d4a06c55adc79697d727"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}