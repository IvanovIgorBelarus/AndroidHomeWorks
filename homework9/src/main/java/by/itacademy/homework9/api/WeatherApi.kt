package by.itacademy.homework9.api

import by.itacademy.homework9.data.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY="205c633ed4e171534278cfece42cf7ff"
interface WeatherApi {
    @GET("{id}")
    fun getCity(
            @Path("id")id: Int,
            @Query("API key")api_key:String= API_KEY
    ): Call<Weather>
}