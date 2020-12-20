package by.itacademy.homework9mvvm.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import by.itacademy.homework9mvvm.data.HourlyWeather
import by.itacademy.homework9mvvm.data.Weather
import by.itacademy.homework9mvvm.data.api.WeatherRepository
import by.itacademy.homework9mvvm.data.db.CityRepository
import by.itacademy.homework9mvvm.data.db.CityRepositoryImpl
import by.itacademy.homework9mvvm.model.HourlyWeatherModel
import by.itacademy.homework9mvvm.model.HourlyWeatherModelMapper
import by.itacademy.homework9mvvm.model.WeatherModel
import by.itacademy.homework9mvvm.model.WeatherModelMapper

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {
    private val context = app.baseContext
    private val weatherModelMapper: WeatherModelMapper = WeatherModelMapper()
    private val hourlyWeatherModelMapper: HourlyWeatherModelMapper= HourlyWeatherModelMapper(context)
    private val degreeListener: DegreeListener = DegreeListenerImpl(context.getSharedPreferences(DEGREE, Context.MODE_PRIVATE))
    private val cityRepository: CityRepository = CityRepositoryImpl(context)
    private val cityListener: CityListener = CityListenerImpl(context.getSharedPreferences(CITY, Context.MODE_PRIVATE))
    private val mutableWeatherLiveData = MutableLiveData<WeatherModel>()
    val weatherLiveData = mutableWeatherLiveData
    private val mutableWeatherAdapterLiveData = MutableLiveData<List<HourlyWeatherModel>>()
    val weatherAdapterLiveData = mutableWeatherAdapterLiveData
    fun getMainWeatherFromApi() {
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
        weatherAdapterLiveData.value=hourlyWeatherModelMapper.invoke(hourlyWeather.hourly)
    }

    private fun getMainWeather(weather: Weather) {
        cityRepository.insertCityInDb(weather)
        mutableWeatherLiveData.value = weatherModelMapper.invoke(weather)
        getWeatherForAdapterFromApi(weather.coord.lat.toString(), weather.coord.lon.toString())
    }

    private fun onError() {

    }

}