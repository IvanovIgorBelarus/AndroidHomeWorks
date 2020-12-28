package by.itacademy.homework10

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework10.databinding.ActivityMainBinding
import by.itacademy.homework10.model.MusicModel
import by.itacademy.homework10.presentation.MainActivityViewModel
import by.itacademy.homework10.presentation.MusicTitleAdapter

const val TAG = "Guv"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val titleAdapter by lazy { MusicTitleAdapter() }
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        prepareRecycler()
    }

    override fun onResume() {
        super.onResume()
        getPermissions()
    }

    private fun getPermissions() {
        val permissionExternal = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionExternal != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1000)
            mainActivityViewModel.getMusicData(this)
        } else {
            mainActivityViewModel.getMusicData(this)
        }
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
        mainActivityViewModel.musicLiveData.observe(this, Observer<List<MusicModel>> { musicModel ->
            titleAdapter.updateAdapter(musicModel)
        })
    }

    private fun prepareRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = titleAdapter
        }
    }
}