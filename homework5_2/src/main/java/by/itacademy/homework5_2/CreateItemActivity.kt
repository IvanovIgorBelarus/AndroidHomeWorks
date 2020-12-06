package by.itacademy.homework5_2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework5_2.data.Contact
import by.itacademy.homework5_2.databinding.ActivityCreateItemBinding
import by.itacademy.homework5_2.multithread.SaveContactsListener
import by.itacademy.homework5_2.multithread.MultiThreadFactory
import by.itacademy.homework5_2.multithread.MultiThreadOperations
import by.itacademy.homework5_2.multithread.RXJAVA_HELPER

class CreateItemActivity : AppCompatActivity(), SaveContactsListener {
    private lateinit var binding: ActivityCreateItemBinding
    private val threadHelper: MultiThreadOperations by lazy { MultiThreadFactory().createHelper(this, RXJAVA_HELPER) }
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
                threadHelper.saveContactInDB(this, contact)
                finish()
            } else {
                Toast.makeText(this, "Add info!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun saveContactInDB(result: String) {
        Toast.makeText(this, "save new contact $result", Toast.LENGTH_SHORT).show()
    }
}