package by.itacademy.homework9.presentation

import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.data.api.WeatherRepository

class MainActivityPresenterImpl(private val mainActivityListener: MainActivityListener) : MainActivityPresenter {
    private val hourlyWeatherModelMapper = HourlyWeatherModelMapper()
    override fun getMainWeather() {
        WeatherRepository.getWeather(
                id = "Minsk",
                onSuccess = ::getMainWeather,
                onError = ::onError
        )
    }

    override fun getWeatherForAdapter() {
        WeatherRepository.getHourlyWeather(
                onSuccess = ::getHourlyWeather,
                onError = ::onError
        )
    }

    private fun getHourlyWeather(hourlyWeather: HourlyWeather) {
        mainActivityListener.getHourlyWeather(hourlyWeatherModelMapper.invoke(hourlyWeather.hourly))
    }

    private fun getMainWeather(weather: Weather) {
        mainActivityListener.getMainWeather(weather)
    }

    private fun onError() {
        mainActivityListener.onError()
    }
}