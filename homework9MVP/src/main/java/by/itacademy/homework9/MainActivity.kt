package by.itacademy.homework9

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework9.databinding.ActivityMainBinding
import by.itacademy.homework9.model.HourlyWeatherModel
import by.itacademy.homework9.model.WeatherModel
import by.itacademy.homework9.presentation.HourlyWeatherAdapter
import by.itacademy.homework9.presentation.MainActivityListener
import by.itacademy.homework9.presentation.MainActivityPresenter
import by.itacademy.homework9.presentation.MainActivityPresenterImpl

class MainActivity : AppCompatActivity(), MainActivityListener {
    private lateinit var binding: ActivityMainBinding
    private val mainActivityPresenter: MainActivityPresenter by lazy { MainActivityPresenterImpl(this, baseContext) }
    private val weatherAdapter by lazy { HourlyWeatherAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            recycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = weatherAdapter
            }
            settingsButton.setOnClickListener { startActivity(Intent(this@MainActivity, SettingsActivity::class.java)) }
            cityButton.setOnClickListener { startActivity(Intent(this@MainActivity, CityActivity::class.java)) }
        }
    }

    override fun onStart() {
        super.onStart()
        mainActivityPresenter.getMainWeatherFromApi()
    }

    override fun getHourlyWeather(hourlyWeatherModel: List<HourlyWeatherModel>) {
        weatherAdapter.update(hourlyWeatherModel)
    }

    override fun getMainWeather(weatherModel: WeatherModel) {
        with(binding) {
            city.text = weatherModel.cityName
            degree.text = weatherModel.temp
            description.text = weatherModel.description
        }
    }

    override fun onError() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }
}