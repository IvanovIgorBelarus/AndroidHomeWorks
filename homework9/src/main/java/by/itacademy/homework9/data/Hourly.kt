package by.itacademy.homework9.data

data class Hourly(
    val clouds: Int,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val rain: Rain,
    val snow: Snow,
    val temp: Double,
    val visibility: Int,
    val wind_speed: Double
)