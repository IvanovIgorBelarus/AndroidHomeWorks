package by.itacademy.homework9

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework9.databinding.ActivityCityBinding
import by.itacademy.homework9.presentation.CityListener
import by.itacademy.homework9.presentation.CityListenerImpl

class CityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCityBinding
    private val cityListener: CityListener by lazy { CityListenerImpl(baseContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
        //    text.text= CitiesRoomDatabase.getDatabase(this@CityActivity).cityDao().allCities.toString()
            newCityButton.setOnClickListener {
                startDialog()
            }
        }
    }

    private fun startDialog() {
        val input = EditText(this@CityActivity)
        with(AlertDialog.Builder(this)) {
            setCancelable(false)
            setTitle("Add city")
            setView(input)
            setPositiveButton("Ok") { dialog, which ->
                this.run {
                    cityListener.saveCity(input.text.toString())
                    finish()
                }
            }
            show()
        }
    }
}