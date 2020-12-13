package by.itacademy.homework9.presentation

import by.itacademy.homework9.model.HourlyWeatherModel
import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.model.WeatherModel

interface MainActivityListener {
    fun getHourlyWeather(hourlyWeatherModel: List<HourlyWeatherModel>)
    fun getMainWeather(weatherModel: WeatherModel)
    fun onError()
}