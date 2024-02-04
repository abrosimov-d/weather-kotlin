package com.example.weather_kotlin;

import androidx.compose.runtime.snapshots.SnapshotStateMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar

object Network {
	val baseUrl = "https://api.openweathermap.org/"
	val appId = "bbaa19e2761f1e488a2cf85c05f3a6f4"
	fun updateData(city: String): SnapshotStateMap<String?, String?> {
		var result = SnapshotStateMap<String?, String?>()
		result.put("base url", baseUrl)
		result.put("appid", appId)
		val retrofit = Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create()).build()
		val weatherApi = retrofit.create(WeatherApi::class.java)

		CoroutineScope(Dispatchers.IO).launch {
			val weather = weatherApi.getWeather(city, appId)
			result.put("weather", weather.weather[0].main)
			result.put("description", weather.weather[0].description)
			result.put("temp", weather.main.temp.toString())
			result.put("feels_like", weather.main.feels_like.toString())
			result.put("temp_min", weather.main.temp_min.toString())
			result.put("temp_max", weather.main.temp_max.toString())
			result.put("pressure", weather.main.pressure.toString())
			result.put("humidity", weather.main.humidity.toString())
			result.put("city", weather.name)
			result.put("time_stamp", Calendar.getInstance().time.toString())
		}
		return result
	}
}
