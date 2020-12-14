package by.itacademy.homework9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework9.databinding.ActivitySettingsBinding
import by.itacademy.homework9.presentation.DegreeListener
import by.itacademy.homework9.presentation.DegreeListenerImpl

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val degreeListener: DegreeListener by lazy { DegreeListenerImpl(baseContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            degree.apply {
                isChecked = degreeListener.loadLanguage() == "metric"
                if (isChecked) {
                    setText(R.string.celsius)
                } else {
                    setText(R.string.fahrenheit)
                }
                setOnCheckedChangeListener { item, isChecked ->
                    if (isChecked) {
                        item.setText(R.string.celsius)
                    } else {
                        item.setText(R.string.fahrenheit)
                    }
                }
            }
            saveButton.setOnClickListener {
                degreeListener.saveSettings(degree.isChecked)
                finish()
            }
        }
    }
}