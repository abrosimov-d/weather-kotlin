package com.example.weather_kotlin

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellokotlin2.Utils
import com.example.weather_kotlin.ui.theme.WeatherKotlinTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			WeatherKotlinTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					dataList()
				}
			}
		}
	}
}

@Composable
fun dataList() {
	var data = remember {
		mutableStateOf(SnapshotStateMap<String?, String?>())
	}
	data.value = updateData()

	var timer = object: CountDownTimer(100000000, 1000) {
		override fun onTick(millisUntilFinished: Long) {
			data.value.put("base url", "https://api.openweathermap.org/")
			data.value.put("current time", Calendar.getInstance().time.toString())
			data.value.put("timer counter", millisUntilFinished.toString())
			data.value.put("lorem word", Utils.getRandomWord())
		}

		override fun onFinish() {
			TODO("Not yet implemented")
		}

	}
	timer.start()

	Column(modifier = Modifier
		.fillMaxSize()
		.padding(10.dp)
	) {
		lazyList(map = data.value)
	}

}

@Composable
fun lazyList(map: SnapshotStateMap<String?, String?>) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			//.clickable { }
	)
	{
		itemsIndexed(map.keys.toMutableList()) {
				_, item ->
			Row(modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,) {
				Text(text = "$item"!!,
					modifier = Modifier
						.padding(10.dp),
					fontSize = 16.sp,

					)
				Text(fontSize = 16.sp,
					text = map.get(item)!!,
					modifier = Modifier
						.padding(10.dp)
				)
			}
			Divider(color = Color.Black, thickness = 0.5.dp)
		}
	}
}

fun updateData(): SnapshotStateMap<String?, String?> {
	var result = SnapshotStateMap<String?, String?>()
	val retrofit = Retrofit.Builder()
		.baseUrl("https://api.openweathermap.org")
		.addConverterFactory(GsonConverterFactory.create()).build()
	val weatherApi = retrofit.create(WeatherApi::class.java)

	CoroutineScope(Dispatchers.IO).launch {
		val weather = weatherApi.getWeather()
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