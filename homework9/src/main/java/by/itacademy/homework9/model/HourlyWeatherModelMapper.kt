package by.itacademy.homework9.model

import by.itacademy.homework9.data.Hourly
import kotlin.math.roundToInt

class HourlyWeatherModelMapper : (List<Hourly>) -> List<HourlyWeatherModel> {
    override fun invoke(hourlyWeather: List<Hourly>) = hourlyWeather.map { item ->
        HourlyWeatherModel(
                temp = item.temp.roundToInt().toString(),
                wind_speed = String.format("wind %s m/s", item.wind_speed.roundToInt()),
                time = DateMapper.getTime(item.dt)

        )
    }
}