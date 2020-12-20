package by.itacademy.homework9mvvm.model

import by.itacademy.homework9mvvm.data.Weather
import kotlin.math.roundToInt

class WeatherModelMapper : (Weather) -> WeatherModel {
    override fun invoke(weather: Weather): WeatherModel =
            WeatherModel(
                    cityName = weather.name,
                    temp = "${weather.main.temp.roundToInt()}",
                    description = weather.weather[0].description
            )
}