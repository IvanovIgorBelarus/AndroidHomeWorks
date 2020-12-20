package by.itacademy.homework9mvvm.model

import by.itacademy.homework9mvvm.data.Weather
import by.itacademy.homework9mvvm.data.db.City


class CityModelMapper: (Weather)->(City) {
    override fun invoke(weather: Weather): City =City(
            city = weather.name,
            lat = weather.coord.lat.toString(),
            lon = weather.coord.lon.toString()
    )}