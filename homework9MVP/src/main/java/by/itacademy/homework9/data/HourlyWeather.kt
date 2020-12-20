package by.itacademy.homework9.data

data class HourlyWeather(
        val hourly: List<Hourly>,
        val lat: Double,
        val lon: Double
)