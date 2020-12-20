package by.itacademy.homework9.presentation

import android.content.Context
import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.data.api.WeatherRepository
import by.itacademy.homework9.data.db.CityRepository
import by.itacademy.homework9.data.db.CityRepositoryImpl
import by.itacademy.homework9.model.HourlyWeatherModelMapper
import by.itacademy.homework9.model.WeatherModelMapper

class MainActivityPresenterImpl(
        private val mainActivityListener: MainActivityListener,
        context: Context,
        private val hourlyWeatherModelMapper: HourlyWeatherModelMapper = HourlyWeatherModelMapper(context),
        private val weatherModelMapper: WeatherModelMapper = WeatherModelMapper(),
        private val degreeListener: DegreeListener = DegreeListenerImpl(context.getSharedPreferences(DEGREE, Context.MODE_PRIVATE)),
        private val cityRepository: CityRepository = CityRepositoryImpl(context),
        private val cityListener: CityListener = CityListenerImpl(context.getSharedPreferences(CITY, Context.MODE_PRIVATE))
) : MainActivityPresenter {

    override fun getMainWeatherFromApi() {
        WeatherRepository.getWeather(
                id = cityListener.loadCity(),
                degree = degreeListener.loadDegree(),
                onSuccess = ::getMainWeather,
                onError = ::onError
        )
    }

    private fun getWeatherForAdapterFromApi(lat: String, lon: String) {
        WeatherRepository.getHourlyWeather(
                lat = lat,
                lon = lon,
                degree = degreeListener.loadDegree(),
                onSuccess = ::getHourlyWeather,
                onError = ::onError
        )
    }

    private fun getHourlyWeather(hourlyWeather: HourlyWeather) {
        mainActivityListener.getHourlyWeather(hourlyWeatherModelMapper.invoke(hourlyWeather.hourly))
    }

    private fun getMainWeather(weather: Weather) {
        cityRepository.insertCityInDb(weather)
        mainActivityListener.getMainWeather(weatherModelMapper.invoke(weather))
        getWeatherForAdapterFromApi(weather.coord.lat.toString(), weather.coord.lon.toString())
    }

    private fun onError() {
        mainActivityListener.onError()
    }
}