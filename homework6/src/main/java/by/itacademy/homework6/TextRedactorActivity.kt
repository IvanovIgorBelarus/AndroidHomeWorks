package by.itacademy.homework6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework6.Data.Companion.dataInstance
import by.itacademy.homework6.databinding.ActivityTextRedactorBinding
import java.io.File

class TextRedactorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextRedactorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextRedactorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        val file = File(filesDir, dataInstance.fileList[position])
        binding.redactorView.setText(file.readText())
        binding.addChanges.setOnClickListener {
            file.bufferedWriter().use { out ->
                out.append(binding.redactorView.text)
                out.newLine()
            }
            finish()
        }
    }
}