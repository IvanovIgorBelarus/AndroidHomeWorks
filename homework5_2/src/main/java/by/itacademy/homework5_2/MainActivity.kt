package by.itacademy.homework5_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework5_2.Data.Companion.instance
import by.itacademy.homework5_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val recycler = binding.recycler
        buttonClick(binding.addItem)


    }
    private fun buttonClick(button: Button) {
        button.setOnClickListener {
            val intent = Intent(this, CreateItemActivity::class.java)
            startActivity(intent)
        }
    }
}