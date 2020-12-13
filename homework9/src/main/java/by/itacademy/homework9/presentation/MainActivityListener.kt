package by.itacademy.homework9.presentation

import by.itacademy.homework9.data.HourlyWeatherModel
import by.itacademy.homework9.data.Weather

interface MainActivityListener {
    fun getHourlyWeather(hourlyWeatherModel: List<HourlyWeatherModel>)
    fun getMainWeather(weather: Weather)
    fun onError()
}