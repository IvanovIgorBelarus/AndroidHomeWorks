package by.itacademy.homework9

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework9.data.db.CitiesRoomDatabase
import by.itacademy.homework9.databinding.ActivityCityBinding
import by.itacademy.homework9.presentation.*
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

class CityActivity : AppCompatActivity(), CityOnClickListener {
    private lateinit var binding: ActivityCityBinding
    private val cityListener: CityListener by lazy { CityListenerImpl(baseContext) }
    private val cityAdapter by lazy { CityAdapter(this) }
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

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        CitiesRoomDatabase.getDatabase(this@CityActivity)
                .getCityDao()
                .getCities()
                .observeOn(mainThread())
                .subscribe { cityAdapter.update(it) }
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
                        cityListener.saveCity(input.text.toString())
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
        cityListener.saveCity(name)
        finish()
    }
}