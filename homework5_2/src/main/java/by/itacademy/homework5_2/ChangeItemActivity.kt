package by.itacademy.homework5_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.homework5_2.databinding.ActivityChangeItemBinding

class ChangeItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeItemBinding.inflate(layoutInflater)
        val position = intent?.getIntExtra("position",0)?:-1

        val contact = getUsersFromDB()[position]
        binding.name.setText(contact.name)
        binding.info.setText(contact.data)
        changeContact(position)
        removeContact(position)
        setContentView(binding.root)
  }

    private fun changeContact(position: Int) {
        binding.back.setOnClickListener {
            val contact = Contact().apply {
                name = binding.name.text.toString()
                data = binding.info.text.toString()
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
    private fun getUsersFromDB():List<Contact>{
        val cursor=(application as App)
                .dbHelper
                .readableDatabase
                .query("contacts",null, null,null,null,null,null)
        if (cursor!=null){
            val indexName=cursor.getColumnIndex("name")
            val indexIsPhone=cursor.getColumnIndex("isPhone")
            val indexData=cursor.getColumnIndex("data")
            val contactList= mutableListOf<Contact>()
            while (cursor.moveToNext()){
                contactList.add(Contact().apply {
                    name=cursor.getString(indexName)
                    isPhone=cursor.getInt(indexIsPhone)
                    data=cursor.getString(indexData)
                })
            }
            cursor.close()
            return contactList
        }
        return emptyList()
    }
}