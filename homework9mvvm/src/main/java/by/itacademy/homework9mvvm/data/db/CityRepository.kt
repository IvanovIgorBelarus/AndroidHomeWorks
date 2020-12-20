package by.itacademy.homework9mvvm.data.db

import by.itacademy.homework9mvvm.data.Weather
import io.reactivex.Flowable

interface CityRepository {
    fun getCitiesFromDb(): Flowable<List<City>>
    fun insertCityInDb(weather: Weather)
}