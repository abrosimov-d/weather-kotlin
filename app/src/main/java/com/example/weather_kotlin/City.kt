package com.example.weather_kotlin

import kotlin.random.Random

object City {
	private val cities = listOf("voronezh", "moscow", "london", "new-york")
	fun randomCity(): String {
		return cities[Random.nextInt(0, cities.size-1)]
	}

	fun nextCity() {

	}
}