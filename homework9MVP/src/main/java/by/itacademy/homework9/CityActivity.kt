package by.itacademy.homework9

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework9.databinding.ActivityCityBinding
import by.itacademy.homework9.presentation.CityActivityPresenter
import by.itacademy.homework9.presentation.CityActivityPresenterImpl
import by.itacademy.homework9.presentation.CityAdapter
import by.itacademy.homework9.presentation.CityOnClickListener

class CityActivity : AppCompatActivity(), CityOnClickListener {
    private lateinit var binding: ActivityCityBinding
    private val cityAdapter by lazy { CityAdapter(this) }
    private val cityPresenter: CityActivityPresenter by lazy { CityActivityPresenterImpl(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            recyclerCity.apply {
                layoutManager = LinearLayoutManager(this@CityActivity)
                adapter = cityAdapter
            }
            newCityButton.setOnClickListener {
                startDialog()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        cityPresenter.updateAdapter(cityAdapter)
    }

    private fun startDialog() {
        val input = EditText(this@CityActivity)
        with(AlertDialog.Builder(this)) {
            setCancelable(false)
            setTitle("Add city")
            setView(input)
            setPositiveButton("Ok") { dialog, which ->
                this.run {
                    if (input.text.isNotEmpty()) {
                        cityPresenter.setCity(input.text.toString())
                        finish()
                    } else {
                        Toast.makeText(this@CityActivity, "Add city name!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            show()
        }
    }

    override fun onClick(name: String) {
        cityPresenter.setCity(name)
        finish()
    }
}