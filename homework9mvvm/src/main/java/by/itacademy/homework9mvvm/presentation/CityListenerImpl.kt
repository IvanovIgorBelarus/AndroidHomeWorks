package by.itacademy.homework9mvvm.presentation

import android.content.SharedPreferences

const val CITY = "CITY"

class CityListenerImpl(private val pref: SharedPreferences) : CityListener {
    override fun saveCity(city: String) {
        pref.edit().putString(CITY, city).apply()
    }

    override fun loadCity() = pref.getString(CITY, "Minsk") ?: "Minsk"
}