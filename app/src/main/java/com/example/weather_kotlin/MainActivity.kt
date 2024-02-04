package com.example.weather_kotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
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
	Timer.init(data)
	data.value = Network.updateData(City.randomCity())

	Box(modifier = Modifier
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

