package by.itacademy.homework6

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework6.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val fileOperations: FileOperations = FileOperationsImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.update.setOnClickListener {
            fileOperations.saveStorageState(binding.internalStorage.isChecked)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("HM2", "${fileOperations.loadStorageState()}")
        with(binding.radioGroup) {
            if (fileOperations.loadStorageState()) {
                check(binding.internalStorage.id)
            } else {
                check(binding.externalStorage.id)
            }
        }
    }
}