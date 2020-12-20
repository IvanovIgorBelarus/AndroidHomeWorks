package by.itacademy.homework9mvvm.data

data class HourlyWeather(
        val hourly: List<Hourly>,
        val lat: Double,
        val lon: Double
)