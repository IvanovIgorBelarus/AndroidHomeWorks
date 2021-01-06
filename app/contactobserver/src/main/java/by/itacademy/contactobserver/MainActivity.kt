package by.itacademy.contactobserver

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.contactobserver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var listContact:List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        search()
        listContact=MyContentResolver.getContacts(applicationContext)
        Log.d("qwe","s=${listContact.size}")
        setRecycler(this)
    }

    override fun onResume() {
        super.onResume()
        itemAdapter.updateItem(listContact)
    }

    private fun setRecycler(activity: MainActivity) {
        val recycler = binding.recycler
        itemAdapter = ItemAdapter()
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
                val newList = listContact.filter { contact -> contact.name.toLowerCase().contains(newText.toString().toLowerCase()) }
                itemAdapter.updateItem(newList)
                return true
            }
        })
    }
}