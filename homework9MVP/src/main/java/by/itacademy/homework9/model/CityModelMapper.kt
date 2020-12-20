package by.itacademy.homework9.model

import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.data.db.City

class CityModelMapper: (Weather)->(City) {
    override fun invoke(weather: Weather): City =City(
            city = weather.name,
            lat = weather.coord.lat.toString(),
            lon = weather.coord.lon.toString()
    )}