package by.itacademy.homework5_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework5_2.data.Contact
import by.itacademy.homework5_2.data.DBOperationsImpl
import by.itacademy.homework5_2.databinding.ActivityMainBinding
import by.itacademy.homework5_2.multithread.UsersListListener
import by.itacademy.homework5_2.multithread.MultiThreadFactory
import by.itacademy.homework5_2.multithread.MultiThreadOperations
import by.itacademy.homework5_2.multithread.RXJAVA_HELPER

class MainActivity : AppCompatActivity(), ListItemActionListener, UsersListListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private val threadHelper: MultiThreadOperations by lazy { MultiThreadFactory().createHelper(this, RXJAVA_HELPER) }
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
        threadHelper.getUsersFromDB(this)
    }

    private fun buttonClick(button: Button) {
        button.setOnClickListener {
            startActivity(Intent(this, CreateItemActivity::class.java))
        }
    }

    private fun setRecycler(activity: MainActivity) {
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
                val newList = DBOperationsImpl(this@MainActivity).getUsersFromDB() //как избавиться от этого костыля??
                        .filter { contact -> contact.name.toLowerCase().contains(newText.toString().toLowerCase()) }
                itemAdapter.updateItem(newList)
                return true
            }
        })
    }

    override fun onItemClicked(number: Int) {
        intent = Intent(this, ChangeItemActivity::class.java)
        intent.putExtra("position", number)
        startActivity(intent)
    }

    override fun getUsersList(list: List<Contact>) {
        itemAdapter.updateItem(list)
    }
}

