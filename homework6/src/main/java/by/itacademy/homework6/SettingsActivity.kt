package by.itacademy.homework6

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework6.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
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
        binding.internalStorage.isChecked = loadStorageState()
        binding.externalStorage.isChecked = !loadStorageState()
    }

    companion object {
        var isInternalStorage: Boolean = true
    }

    private fun saveStorageState(storageState: Boolean) {
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("1", storageState)
        editor.apply()
    }

    private fun loadStorageState(): Boolean {
        val pref = getPreferences(Context.MODE_PRIVATE)
        return pref.getBoolean("1", true)
    }
}