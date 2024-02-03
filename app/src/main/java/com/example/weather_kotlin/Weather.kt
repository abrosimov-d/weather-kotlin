package com.example.weather_kotlin

data class Coord(
	var lat: Float,
	var lon: Float,
)

data class Weather(
	var id: Int,
	var main: String,
	var description: String,
	var icon: String
)

data class Main(
	var temp: Float,
	var feels_like: Float,
	var temp_min: Float
	var temp_max: Float,
	var pressure: Int,
	var humidity: Int
)

data class WeatherResult(
	var coord: Coord,
	var weather: Weather,
	var main: Main
)

/*

 "https://api.openweathermap.org/data/2.5/weather?q=voronezh&appid=bbaa19e2761f1e488a2cf85c05f3a6f4&units=metric"

{
  "coord": {
    "lon": 39.17,
    "lat": 51.6664
  },
  "weather": [
    {
      "id": 600,
      "main": "Snow",
      "description": "light snow",
      "icon": "13n"
    }
  ],
  "base": "stations",
  "main": {
    "temp": -0.01,
    "feels_like": -5.46,
    "temp_min": -0.86,
    "temp_max": 0.01,
    "pressure": 997,
    "humidity": 93
  },
  "visibility": 10000,
  "wind": {
    "speed": 6,
    "deg": 210
  },
  "snow": {
    "1h": 0.19
  },
  "clouds": {
    "all": 100
  },
  "dt": 1706982827,
  "sys": {
    "type": 1,
    "id": 9034,
    "country": "RU",
    "sunrise": 1706936418,
    "sunset": 1706969625
  },
  "timezone": 10800,
  "id": 472045,
  "name": "Voronezh",
  "cod": 200
}

 */