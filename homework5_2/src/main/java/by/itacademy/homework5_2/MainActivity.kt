package by.itacademy.homework5_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework5_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ListItemActionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        search()
        buttonClick(binding.addItem)
        setRecycler(this)
    }

    override fun onResume() {
        super.onResume()
        itemAdapter.updateItem(getUsersFromDB())
    }

    private fun getUsersFromDB():List<Contact>{
        val cursor=(applicationContext as App)
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

    private fun buttonClick(button: Button) {
        button.setOnClickListener {
            startActivity(Intent(this, CreateItemActivity::class.java))
        }
    }

    private fun setRecycler( activity: MainActivity) {
        val recycler = binding.recycler
        itemAdapter = ItemAdapter(activity)
        recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = itemAdapter
        }
    }

    private fun search() {
        binding.searchContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newList = getUsersFromDB().filter { contact -> contact.name.toLowerCase().contains(newText.toString().toLowerCase()) }
                itemAdapter.updateItem(newList)
                return true
            }
        })
    }

    override fun onItemClicked(number: Int) {
//        intent = Intent(this, ChangeItemActivity::class.java)
//        intent.putExtra("position", number)
//        startActivity(intent)
    }
}