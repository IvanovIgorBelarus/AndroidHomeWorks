package by.itacademy.contacts.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.contacts.data.Contact
import by.itacademy.contacts.presentation.DBOperations
import by.itacademy.contacts.presentation.DBOperationsImpl

import by.itacademy.contacts.databinding.ActivityCreateItemBinding

class CreateItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateItemBinding
    private val dbOperations: DBOperations = DBOperationsImpl()
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

            if (nameText != "" && dataText != "") {
                val contact: Contact = Contact().apply {
                    name = nameText
                    data = dataText
                }
                if (email) contact.isPhone = 0
                dbOperations.saveContactInDB(applicationContext, contact)
                finish()
            } else {
                Toast.makeText(this, "Add info!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}