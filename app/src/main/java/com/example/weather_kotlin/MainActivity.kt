package com.example.weather_kotlin

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_kotlin.ui.theme.WeatherKotlinTheme
import java.sql.Time
import java.util.Calendar

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

	var timer = object: CountDownTimer(100000000, 1000) {
		override fun onTick(millisUntilFinished: Long) {
			data.value.put("time", Calendar.getInstance().time.toString())
			data.value.put("counter", millisUntilFinished.toString())
		}

		override fun onFinish() {
			TODO("Not yet implemented")
		}

	}
	timer.start()


	Box(modifier = Modifier.fillMaxSize()) {
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
		}
	}
}