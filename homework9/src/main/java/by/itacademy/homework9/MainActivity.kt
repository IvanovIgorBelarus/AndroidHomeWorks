package by.itacademy.homework9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework9.data.HourlyWeather
import by.itacademy.homework9.data.Weather
import by.itacademy.homework9.databinding.ActivityMainBinding
import by.itacademy.homework9.presentation.HourlyWeatherAdapter
import by.itacademy.homework9.presentation.MainActivityListener
import by.itacademy.homework9.presentation.MainActivityPresenter
import by.itacademy.homework9.presentation.MainActivityPresenterImpl
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), MainActivityListener {
    private lateinit var binding: ActivityMainBinding
    private val mainActivityPresenter: MainActivityPresenter = MainActivityPresenterImpl(this)
    private val weatherAdapter by lazy { HourlyWeatherAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(mainActivityPresenter) {
            getMainWeather()
            getWeatherForAdapter()
        }
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = weatherAdapter
        }
    }

    override fun getHourlyWeather(hourlyWeather: HourlyWeather) {
        // Log.d("HM9", "$hourlyWeather")
        weatherAdapter.update(hourlyWeather.hourly)
    }

    override fun getMainWeather(weather: Weather) {
        // Log.d("HM9", "$weather")
        with(binding) {
            city.text = weather.name
            degree.text = "${weather.main.temp.roundToInt()}"
            description.text = weather.weather[0].description
        }
    }

    override fun onError() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }
}