package by.itacademy.homework6

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework6.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val fileOperations: FileOperations = FileOperationsImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.update.setOnClickListener {
            isInternalStorage = binding.internalStorage.isChecked
            saveStorageState(isInternalStorage)
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
            binding.internalStorage.isChecked = fileOperations.loadStorageState(applicationContext)
    }
    private fun saveStorageState(storageState: Boolean) {
        val pref = getSharedPreferences("settingStorage", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("1", storageState)
        editor.apply()
    }
    companion object {
        var isInternalStorage: Boolean = true
    }
}