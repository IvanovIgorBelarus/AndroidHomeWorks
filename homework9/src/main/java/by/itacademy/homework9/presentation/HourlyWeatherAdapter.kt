package by.itacademy.homework9.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.homework9.databinding.WeatherRecyclerBinding
import by.itacademy.homework9.model.HourlyWeatherModel

class HourlyWeatherAdapter(
        private var hourlyWeather: MutableList<HourlyWeatherModel> = mutableListOf()
) : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherHolder =
            HourlyWeatherHolder(WeatherRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HourlyWeatherHolder, position: Int) {
        holder.bind(hourlyWeather[position])
    }

    override fun getItemCount(): Int = hourlyWeather.size

    fun update(list: List<HourlyWeatherModel>) {
        hourlyWeather = list.toMutableList()
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