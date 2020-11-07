package by.itacademy.homework5_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework5_2.Data.Companion.instance
import by.itacademy.homework5_2.databinding.ActivityChangeItemBinding

class ChangeItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeItemBinding.inflate(layoutInflater)
        val position = if (intent != null) intent.getIntExtra("position", 0) else -1

        val contact = instance.getContacts()[position]
        binding.name.setText(contact.name)
        binding.info.setText(contact.data)
        changeContact(position)
        removeContact(position)
        setContentView(binding.root)
    }

    private fun changeContact(position: Int) {
        binding.back.setOnClickListener {
            val contact = Contact()
            contact.apply {
                name = binding.name.text.toString()
                data = binding.info.text.toString()
                isPhone = instance.getContacts()[position].isPhone
            }
            instance.setContact(position, contact)
            finish()
        }
    }

    private fun removeContact(position: Int) {
        binding.remove.setOnClickListener {
            instance.removeContact(position)
            finish()
        }
    }
}