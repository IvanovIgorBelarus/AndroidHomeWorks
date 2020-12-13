package by.itacademy.homework9.model

import by.itacademy.homework9.data.Weather

class WeatherModelMapper:(Weather)->WeatherModel {
    override fun invoke(weather: Weather):WeatherModel {
       return WeatherModel(
               cityName = weather.name,
               temp = "{$weather.main.temp}",
               description = weather.weather[0].description
       )
    }
}