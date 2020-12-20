package by.itacademy.homework9mvvm.model

import android.content.Context
import by.itacademy.homework9mvvm.data.Hourly
import by.itacademy.homework9mvvm.presentation.DEGREE
import by.itacademy.homework9mvvm.presentation.DegreeListener
import by.itacademy.homework9mvvm.presentation.DegreeListenerImpl
import kotlin.math.roundToInt


class HourlyWeatherModelMapper(private val context: Context) : (List<Hourly>) -> List<HourlyWeatherModel> {
    override fun invoke(hourlyWeather: List<Hourly>) = hourlyWeather.map { item ->
        HourlyWeatherModel(
                temp = item.temp.roundToInt().toString(),
                wind_speed = String.format("wind %s %s", item.wind_speed.roundToInt(), getMetric()),
                time = DateMapper.getTime(item.dt)

        )
    }

    private fun getMetric(): String {
        val degreeListener: DegreeListener = DegreeListenerImpl(context.getSharedPreferences(DEGREE, Context.MODE_PRIVATE))
        if (degreeListener.loadDegree() != "metric") {
            return "m/h"
        }
        return "m/s"
    }
}