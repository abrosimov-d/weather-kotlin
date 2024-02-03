package com.example.weather_kotlin

import com.example.weather_kotlin.WeatherResult
import retrofit2.http.GET

interface WeatherApi {
	@GET("data/2.5/weather?q=voronezh&appid=bbaa19e2761f1e488a2cf85c05f3a6f4&units=metric")
	suspend fun getWeather(): WeatherResult
}