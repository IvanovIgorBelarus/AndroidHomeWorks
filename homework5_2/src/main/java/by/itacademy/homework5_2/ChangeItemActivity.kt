package by.itacademy.homework5_2


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework5_2.data.Contact
import by.itacademy.homework5_2.data.DBOperationsImpl
import by.itacademy.homework5_2.databinding.ActivityChangeItemBinding
import by.itacademy.homework5_2.multithread.ChangeContactsListener
import by.itacademy.homework5_2.multithread.MultiThreadFactory
import by.itacademy.homework5_2.multithread.MultiThreadOperations
import by.itacademy.homework5_2.multithread.RXJAVA_HELPER

class ChangeItemActivity : AppCompatActivity(), ChangeContactsListener {
    private lateinit var binding: ActivityChangeItemBinding
    private val threadHelper: MultiThreadOperations by lazy { MultiThreadFactory().createHelper(this, RXJAVA_HELPER) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeItemBinding.inflate(layoutInflater)
        val position = intent?.getIntExtra("position", 0) ?: -1
        val contact = DBOperationsImpl(this).getUsersFromDB()[position]  //нужно избавиться от костыля...
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
            threadHelper.changeContact(this, newContact, position)
            finish()
        }
    }

    private fun removeContact(position: Int) {
        binding.remove.setOnClickListener {
            threadHelper.removeContact(this, position)
            finish()
        }
    }

    override fun changeContact(result: String) {
        Toast.makeText(this, "change contact $result", Toast.LENGTH_SHORT).show()
    }

    override fun removeContact() {
        Toast.makeText(this, "contact was deleted", Toast.LENGTH_SHORT).show()
    }
}
