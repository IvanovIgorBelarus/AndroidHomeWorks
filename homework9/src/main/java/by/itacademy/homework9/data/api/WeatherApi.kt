package by.itacademy.homework9.data.api

import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "91fca9855dff1b903251cda79b7674dc"

interface WeatherApi {
    @GET("weather")
    fun getWeather(
            @Query("q") id: String,
            @Query("appid") api_key: String = API_KEY,
            @Query("units") units: String = "metric"
    ): Call<Weather>

    @GET("onecall")
    fun getHourlyWeather(
            @Query("lat") lat: Double = 53.9,
            @Query("lon") lon: Double = 27.0,
            @Query("units") units: String = "metric",
            @Query("exclude") exclude: String = "alerts",
            @Query("appid") api_key: String = API_KEY
    ): Call<HourlyWeather>
}