package by.itacademy.homework9mvvm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework9mvvm.databinding.ActivityMainBinding
import by.itacademy.homework9mvvm.model.HourlyWeatherModel
import by.itacademy.homework9mvvm.model.WeatherModel
import by.itacademy.homework9mvvm.presentation.HourlyWeatherAdapter
import by.itacademy.homework9mvvm.presentation.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherAdapter by lazy { HourlyWeatherAdapter() }

    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
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
        mainActivityViewModel.getMainWeatherFromApi()
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(MainActivityViewModel::class.java)
        mainActivityViewModel.weatherLiveData.observe(this, Observer<WeatherModel> { weatherModel ->
            with(binding) {
                city.text = weatherModel.cityName
                degree.text = weatherModel.temp
                description.text = weatherModel.description
            }
        })
        mainActivityViewModel.weatherAdapterLiveData
                .observe(this, Observer<List<HourlyWeatherModel>> { hourlyweather -> weatherAdapter.update(hourlyweather) })
    }
}