package by.itacademy.homework5_2

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework5_2.databinding.ActivityCreateItemBinding

class CreateItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backFromCreate.setOnClickListener { finish() }
        addContact(binding.addButton)
    }

    private fun addContact(button: Button) {
        button.setOnClickListener {

            val nameText = binding.name.text.toString()
            val dataText = binding.numberOrEmail.text.toString()
            val email = binding.addEmail.isChecked
            val phone = binding.addPhone.isChecked

            if (nameText != "" && dataText != "") {
                val contact: Contact = Contact().apply {
                    name = nameText
                    data = dataText
                }
                if (email) contact.isPhone = 0
                saveContactInDB(contact)
                //instance.addContact(contact)
                finish()
            } else {
                Toast.makeText(this, "Add info!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveContactInDB(contact: Contact) {
        val contentValues = ContentValues().apply {
            put("name", contact.name)
            put("isPhone", contact.isPhone)
            put("data", contact.data)
        }
        (applicationContext as App)
                .dbHelper
                .writableDatabase
                .insert("contacts", null, contentValues)
    }
}