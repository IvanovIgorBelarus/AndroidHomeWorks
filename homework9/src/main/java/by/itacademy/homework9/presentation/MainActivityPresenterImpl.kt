package by.itacademy.homework9.presentation

import android.content.Context
import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.data.api.WeatherRepository
import by.itacademy.homework9.data.db.CitiesRoomDatabase
import by.itacademy.homework9.model.CityModelMapper
import by.itacademy.homework9.model.HourlyWeatherModelMapper
import by.itacademy.homework9.model.WeatherModelMapper
import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

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
    private val cityDao = citiesRoomDatabase.getCityDao()
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
        Observable.just(cityDao)
                .subscribeOn(Schedulers.io())
                .subscribe { it.insert(cityModelMapper.invoke(weather)) }
        mainActivityListener.getMainWeather(weatherModelMapper.invoke(weather))
    }

    private fun onError() {
        mainActivityListener.onError()
    }
}