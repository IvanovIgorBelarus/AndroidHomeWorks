package by.itacademy.homework9.presentation

import android.content.Context

const val CITY = "CITY"

class CityListenerImpl(private val context: Context) : CityListener {
    override fun saveCity(city: String) {
        val pref = context.getSharedPreferences(CITY, Context.MODE_PRIVATE)
        pref.edit().putString(CITY, city).apply()
    }

    override fun loadCity()
    = context.getSharedPreferences(CITY, Context.MODE_PRIVATE).getString(CITY, "Minsk")?: ""
}