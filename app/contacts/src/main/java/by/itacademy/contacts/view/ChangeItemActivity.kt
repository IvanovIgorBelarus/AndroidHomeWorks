package by.itacademy.contacts.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.contacts.data.Contact
import by.itacademy.contacts.presentation.DBOperations
import by.itacademy.contacts.presentation.DBOperationsImpl
import by.itacademy.contacts.databinding.ActivityChangeItemBinding


class ChangeItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeItemBinding
    private val dbOperations: DBOperations = DBOperationsImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeItemBinding.inflate(layoutInflater)
        val position = intent?.getIntExtra("position", 0) ?: -1
        val contact = dbOperations.getUsersFromDB(applicationContext)[position]
        with(binding) {
            name.setText(contact.name)
            info.setText(contact.data)
        }
        changeContact(contact, position)
        removeContact(position)
        setContentView(binding.root)
    }

    private fun changeContact(contact: Contact, position: Int) {
        binding.back.setOnClickListener {
            val newContact = Contact().apply {
                name = binding.name.text.toString()
                data = binding.info.text.toString()
                isPhone = contact.isPhone
            }
            dbOperations.changeContact(applicationContext, newContact, position)
            finish()
        }
    }

    private fun removeContact(position: Int) {
        binding.remove.setOnClickListener {
            dbOperations.removeContact(applicationContext, position)
            finish()
        }
    }
}