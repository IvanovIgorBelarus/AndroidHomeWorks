package by.itacademy.homework9.presentation

import android.content.Context
import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.data.api.WeatherRepository
import by.itacademy.homework9.data.db.CitiesRoomDatabase
import by.itacademy.homework9.model.CityModelMapper
import by.itacademy.homework9.model.HourlyWeatherModelMapper
import by.itacademy.homework9.model.WeatherModelMapper

class MainActivityPresenterImpl(
        private val mainActivityListener: MainActivityListener,
        context: Context,
        private val hourlyWeatherModelMapper: HourlyWeatherModelMapper = HourlyWeatherModelMapper(),
        private val weatherModelMapper: WeatherModelMapper = WeatherModelMapper(),
        private val degreeListener: DegreeListener = DegreeListenerImpl(context),
        private val cityModelMapper: CityModelMapper = CityModelMapper(),
        private val cityListener: CityListener = CityListenerImpl(context)
) : MainActivityPresenter {
    private val citiesRoomDatabase = CitiesRoomDatabase.getDatabase(context)
    private val cityDao = citiesRoomDatabase.cityDao()
    override fun getMainWeatherFromApi() {
        WeatherRepository.getWeather(
                id = cityListener.loadCity(),
                degree = degreeListener.loadDegree(),
                onSuccess = ::getMainWeather,
                onError = ::onError
        )
    }

    override fun getWeatherForAdapterFromApi() {
        WeatherRepository.getHourlyWeather(
                degree = degreeListener.loadDegree(),
                onSuccess = ::getHourlyWeather,
                onError = ::onError
        )
    }

    private fun getHourlyWeather(hourlyWeather: HourlyWeather) {
        mainActivityListener.getHourlyWeather(hourlyWeatherModelMapper.invoke(hourlyWeather.hourly))
    }

    private fun getMainWeather(weather: Weather) {
       //сохранять тут
        mainActivityListener.getMainWeather(weatherModelMapper.invoke(weather))
    }

    private fun onError() {
        mainActivityListener.onError()
    }
}