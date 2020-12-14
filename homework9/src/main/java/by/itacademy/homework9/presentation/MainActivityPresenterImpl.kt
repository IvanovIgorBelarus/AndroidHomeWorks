package by.itacademy.homework9.presentation

import android.content.Context
import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.data.api.WeatherRepository
import by.itacademy.homework9.model.HourlyWeatherModelMapper
import by.itacademy.homework9.model.WeatherModelMapper

class MainActivityPresenterImpl(private val mainActivityListener: MainActivityListener, private val context: Context) : MainActivityPresenter {
    private val hourlyWeatherModelMapper = HourlyWeatherModelMapper()
    private val weatherModelMapper=WeatherModelMapper()
    private val degreeListener:DegreeListener=DegreeListenerImpl(context)
    override fun getMainWeather() {
        WeatherRepository.getWeather(
                id = "Minsk",
                degree = degreeListener.loadLanguage(),
                onSuccess = ::getMainWeather,
                onError = ::onError
        )
    }

    override fun getWeatherForAdapter() {
        WeatherRepository.getHourlyWeather(
                degree = degreeListener.loadLanguage(),
                onSuccess = ::getHourlyWeather,
                onError = ::onError
        )
    }

    private fun getHourlyWeather(hourlyWeather: HourlyWeather) {
        mainActivityListener.getHourlyWeather(hourlyWeatherModelMapper.invoke(hourlyWeather.hourly))
    }

    private fun getMainWeather(weather: Weather) {
        mainActivityListener.getMainWeather(weatherModelMapper.invoke(weather))
    }

    private fun onError() {
        mainActivityListener.onError()
    }
}