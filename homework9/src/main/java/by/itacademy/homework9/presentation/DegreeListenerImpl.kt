package by.itacademy.homework9.presentation

import android.content.SharedPreferences

const val DEGREE = "degree"

class DegreeListenerImpl(private val pref: SharedPreferences) : DegreeListener {
    override fun saveSettings(isMetric: Boolean) {
        with(pref.edit()) {
            if (isMetric) {
                putString(DEGREE, "metric")
            } else {
                putString(DEGREE, "imperial")
            }
        }.apply()
    }

    override fun loadDegree() = pref.getString(DEGREE, "metric") ?: ""
}