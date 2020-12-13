package by.itacademy.homework9.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.homework9.data.Hourly
import by.itacademy.homework9.databinding.WeatherRecyclerBinding
import kotlin.math.roundToInt

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherHolder>() {
    private var hourlyWeather = mutableListOf<Hourly>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherHolder =
            HourlyWeatherHolder(WeatherRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HourlyWeatherHolder, position: Int) {
        holder.bind(hourlyWeather[position])
    }

    override fun getItemCount(): Int = hourlyWeather.size
    fun update(list: List<Hourly>) {
        hourlyWeather.addAll(list)
        notifyDataSetChanged()
    }

    class HourlyWeatherHolder(private val binding: WeatherRecyclerBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(hourly: Hourly) {
            with(binding) {
                degree.text = String.format("%s C",hourly.temp.roundToInt().toString())
                wind.text= String.format("скорость ветра %s м/с",hourly.temp.roundToInt())
            }
        }
    }
}