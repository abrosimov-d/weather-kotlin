package com.example.weather_kotlin

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.example.hellokotlin2.Utils
import java.util.Calendar

object Timer {
	fun init(data: MutableState<SnapshotStateMap<String?, String?>>) {
		var timer = object: CountDownTimer(100000000, 1000) {
			override fun onTick(millisUntilFinished: Long) {
				data.value.put("current time", Calendar.getInstance().time.toString())
				data.value.put("lorem word", Utils.getRandomWord())
			}

			override fun onFinish() {
				TODO("Not yet implemented")
			}
		}
		timer.start()
	}
}