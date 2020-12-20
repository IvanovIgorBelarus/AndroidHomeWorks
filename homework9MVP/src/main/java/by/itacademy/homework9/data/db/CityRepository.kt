package by.itacademy.homework9.data.db

import by.itacademy.homework9.data.Weather
import io.reactivex.Flowable

interface CityRepository {
    fun getCitiesFromDb(): Flowable<List<City>>
    fun insertCityInDb(weather: Weather)
}