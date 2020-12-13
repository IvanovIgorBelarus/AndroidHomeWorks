package by.itacademy.homework9.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.homework9.model.HourlyWeatherModel
import by.itacademy.homework9.databinding.WeatherRecyclerBinding

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherHolder>() {
    private var hourlyWeather = mutableListOf<HourlyWeatherModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherHolder =
            HourlyWeatherHolder(WeatherRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HourlyWeatherHolder, position: Int) {
        holder.bind(hourlyWeather[position])
    }

    override fun getItemCount(): Int = hourlyWeather.size
    fun update(list: List<HourlyWeatherModel>) {
        hourlyWeather.addAll(list)
        notifyDataSetChanged()
    }

    class HourlyWeatherHolder(private val binding: WeatherRecyclerBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(hourly: HourlyWeatherModel) {
            with(binding) {
                degree.text = hourly.temp
                wind.text = hourly.wind_speed
                time.text = hourly.time
            }
        }
    }
}