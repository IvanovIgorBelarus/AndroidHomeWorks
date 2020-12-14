package by.itacademy.homework9.presentation

import android.content.Context

const val DEGREE = "degree"

class DegreeListenerImpl(private val context: Context) : DegreeListener {
    override fun saveSettings(isMetric: Boolean) {
        val pref = context.getSharedPreferences(DEGREE, Context.MODE_PRIVATE)
        with(pref.edit()) {
            if (isMetric) {
                putString(DEGREE, "metric")
            } else {
                putString(DEGREE, "imperial")
            }
        }.apply()
    }

    override fun loadDegree() =
            context.getSharedPreferences(DEGREE, Context.MODE_PRIVATE)
                    .getString(DEGREE, "metric") ?: ""
}