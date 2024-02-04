package com.example.weather_kotlin

import com.example.weather_kotlin.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
	@GET("data/2.5/weather?units=metric")
	suspend fun getWeather(
		@Query("q") city: String,
		@Query("appid") appid: String): WeatherResult
}