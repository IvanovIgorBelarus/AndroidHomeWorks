package by.itacademy.homework9.presentation

import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather

interface MainActivityListener {
    fun getHourlyWeather(hourlyWeather: HourlyWeather)
    fun getMainWeather(weather: Weather)
    fun onError()
}