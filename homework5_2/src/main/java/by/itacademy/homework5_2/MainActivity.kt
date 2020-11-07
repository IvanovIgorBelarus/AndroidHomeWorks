package by.itacademy.homework5_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework5_2.Data.Companion.instance
import by.itacademy.homework5_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Observer, ListItemActionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instance.addSubscriber(this)

        setSupportActionBar(binding.toolbar)
        search()
        buttonClick(binding.addItem)
    }

    override fun onStart() {
        super.onStart()
        setRecycler(instance.getContacts(), this)
    }

    override fun onDestroy() {
        super.onDestroy()
        instance.removeSubscriber(this)
    }

    private fun buttonClick(button: Button) {
        button.setOnClickListener {
            startActivity(Intent(this, CreateItemActivity::class.java))
        }
    }

    private fun setRecycler(contactList: List<Contact>, activity: MainActivity) {
        val recycler = binding.recycler
        itemAdapter = ItemAdapter(contactList, activity)
        recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = itemAdapter
        }
    }

    private fun search() {
        binding.searchContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newList = instance.getContacts().filter { contact -> contact.name.toLowerCase().contains(newText.toString().toLowerCase()) }
                setRecycler(newList, this@MainActivity)
                return true
            }
        })
    }

    override fun notify(position: Int, operation: Int) {
        when (operation) {
            instance.CHANGE -> itemAdapter.notifyItemChanged(position)
            instance.REMOVE -> itemAdapter.notifyItemRemoved(position)
        }
    }

    override fun onItemClicked(number: Int) {
        intent = Intent(this, ChangeItemActivity::class.java)
        intent.putExtra("position", number)
        startActivity(intent)
    }
}